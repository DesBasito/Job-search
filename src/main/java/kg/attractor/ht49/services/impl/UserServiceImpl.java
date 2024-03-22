package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dto.users.EditUserDto;
import kg.attractor.ht49.dto.users.UserCreationDto;
import kg.attractor.ht49.dao.UserDao;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.enums.AccountTypes;
import kg.attractor.ht49.exceptions.AlreadyExistsException;
import kg.attractor.ht49.exceptions.UserNotFoundException;
import kg.attractor.ht49.models.User;
import kg.attractor.ht49.services.UserService;
import kg.attractor.ht49.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final FileUtil util;

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userDao.getAllUsers();
        List<UserDto> dtos = new ArrayList<>();
        users.forEach(e -> dtos.add(getUserDto(e)));
        return dtos;
    }


    @Override
    public UserDto getUserByEmail(String email) {
        User user = userDao.getUserByEmail(email).orElseThrow(() -> new UserNotFoundException("user with email: " + email + " does not exists"));
        return getUserDto(user);
    }


    @Override
    public UserDto getUserById(Long id) {
        User user = userDao.getUserById(id).orElseThrow(()->new UserNotFoundException("user with id: " + id + " does not exists"));
        return getUserDto(user);
    }

    @Override
    public void createUser(UserCreationDto dto) {
        if (userDao.getUserByEmail(dto.getEmail()).isPresent()) {
            throw new AlreadyExistsException("User with email:" + dto.getEmail() + " already exists.");
        }
        if (!dto.getAccType().equals(AccountTypes.EMPLOYER.toString()) || !dto.getAccType().equals(AccountTypes.EMPLOYEE.toString())) {
            throw new IllegalArgumentException("Unsupported type");
        }
        String fileName = null;
        if (!dto.getAvatar().isEmpty()) {
            if (!Objects.requireNonNull(dto.getAvatar().getContentType()).matches("png|jpeg|jpg")) {
                throw new IllegalArgumentException("Unsupported img types (should be: \"png|jpeg|jpg\")");
            }
            fileName = util.saveUploadedFile(dto.getAvatar(), "/images");
        }

        User user = User.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .age(dto.getAge())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .phoneNumber(dto.getPhoneNumber())
                .accType(dto.getAccType())
                .avatar(fileName)
                .build();
        userDao.createUser(user);
    }

    @Override
    public List<UserDto> getUserByName(String name, AccountTypes type) {
        List<User> users = userDao.getUserByName(name, type);
        List<UserDto> dtos = new ArrayList<>();
        users.forEach(e -> dtos.add(getUserDto(e)));
        return dtos;
    }

    @Override
    public UserDto getUserByPhone(String phone) {
        User user = userDao.getUserByPhone(phone).orElseThrow(() -> new UserNotFoundException("User by phone num: " + phone + " not found"));
        return getUserDto(user);
    }

    @Override
    public Long getUserId(String email) {
        User user1 = userDao.getUserByEmail(email).orElseThrow(() -> new UserNotFoundException("User by email " + email + " not found"));
        return user1.getId();
    }

    @Override
    public void editUser(EditUserDto user) {
        String fileName = util.saveUploadedFile(user.getAvatar(), "/images");
        User user1 = User.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .avatar(fileName)
                .build();
        userDao.editUser(user1);
    }

    @Override
    public List<UserDto> getUsersByType(String type) {
        List<User> userList;
        if (type.equalsIgnoreCase(AccountTypes.EMPLOYEE.toString())) {
            userList = userDao.getUsersByTypeAcc(AccountTypes.EMPLOYEE);
        } else if (type.equalsIgnoreCase(AccountTypes.EMPLOYER.toString())) {
            userList = userDao.getUsersByTypeAcc(AccountTypes.EMPLOYER);
        } else {
            log.error("Users by type {} not found", type);
            throw new IllegalArgumentException("Users by type " + type + " not found");
        }
        List<UserDto> dtos = new ArrayList<>();
        userList.forEach(e -> dtos.add(getUserDto(e)));
        return dtos;
    }

    @Override
    public User getRawUserByEmail(String email) {
        return userDao.getUserByEmail(email).orElse(null);
    }

    @Override
    public List<UserDto> getEmpl(AccountTypes types) {
        List<User> employees = userDao.getUsersByTypeAcc(types);
        List<UserDto> dtos = new ArrayList<>();
        employees.forEach(e -> dtos.add(getUserDto(e)));
        return dtos;
    }

    @Override
    public UserDto getEmplByEmail(String email, AccountTypes accountTypes) {
        User user = userDao.getEmplByEmail(email, accountTypes).orElseThrow(() -> new UserNotFoundException("user by email: " + email + " not found"));
        return getUserDto(user);
    }

    @Override
    public UserDto getEmplByPhone(String strip, AccountTypes accountTypes) {
        User user = userDao.getEmplByPhone(strip, accountTypes).orElseThrow(() -> new NoSuchElementException("user with phone number: " + strip + " not found"));
        return getUserDto(user);
    }


    private UserDto getUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .avatar(user.getAvatar())
                .accType(user.getAccType())
                .build();
    }
}
