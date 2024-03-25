package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dto.users.EditUserDto;
import kg.attractor.ht49.dto.users.UserCreationDto;
import kg.attractor.ht49.dao.UserDao;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.enums.AccountTypes;
import kg.attractor.ht49.exceptions.AlreadyExistsException;
import kg.attractor.ht49.exceptions.UserNotFoundException;
import kg.attractor.ht49.models.UserModel;
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
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final FileUtil util;

    @Override
    public List<UserDto> getUsers() {
        List<UserModel> userModels = userDao.getAllUsers();
        List<UserDto> dtos = new ArrayList<>();
        userModels.forEach(e -> dtos.add(getUserDto(e)));
        return dtos;
    }


    @Override
    public UserDto getUserByEmail(String email) {
        UserModel userModel = userDao.getUserByEmail(email).orElseThrow(() -> new UserNotFoundException("user with email: " + email + " does not exists"));
        return getUserDto(userModel);
    }


    @Override
    public UserDto getUserById(Long id) {
        UserModel userModel = userDao.getUserById(id).orElseThrow(()->new UserNotFoundException("user with id: " + id + " does not exists"));
        return getUserDto(userModel);
    }

    @Override
    public void createUser(UserCreationDto dto) {
        if (userDao.getUserByEmail(dto.getEmail()).isPresent()) {
            throw new AlreadyExistsException("User with email:" + dto.getEmail() + " already exists.");
        }
        String fileName = null;
        if (!dto.getAvatar().isEmpty()) {
            if (!Objects.requireNonNull(dto.getAvatar().getContentType()).matches("png|jpeg|jpg")) {
                throw new IllegalArgumentException("Unsupported img types (should be: \"png|jpeg|jpg\")");
            }
            fileName = util.saveUploadedFile(dto.getAvatar(), "/images");
        }

        UserModel userModel = UserModel.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .age(dto.getAge())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .phoneNumber(dto.getPhoneNumber())
                .enabled(true)
                .avatar(fileName)
                .build();
        userDao.createUser(userModel);
    }

    @Override
    public List<UserDto> getUserByName(String name, AccountTypes type) {
        List<UserModel> userModels = userDao.getUserByName(name, type);
        List<UserDto> dtos = new ArrayList<>();
        userModels.forEach(e -> dtos.add(getUserDto(e)));
        return dtos;
    }

    @Override
    public UserDto getUserByPhone(String phone) {
        UserModel userModel = userDao.getUserByPhone(phone).orElseThrow(() -> new UserNotFoundException("User by phone num: " + phone + " not found"));
        return getUserDto(userModel);
    }

    @Override
    public Long getUserId(String email) {
        UserModel userModel1 = userDao.getUserByEmail(email).orElseThrow(() -> new UserNotFoundException("User by email " + email + " not found"));
        return userModel1.getId();
    }

    @Override
    public void editUser(EditUserDto user) {
        String fileName = util.saveUploadedFile(user.getAvatar(), "/images");
        UserModel userModel1 = UserModel.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .avatar(fileName)
                .build();
        userDao.editUser(userModel1);
    }

    @Override
    public List<UserDto> getUsersByType(String type) {
        List<UserModel> userModelList;
        if (type.equalsIgnoreCase(AccountTypes.EMPLOYEE.toString())) {
            userModelList = userDao.getUsersByTypeAcc(AccountTypes.EMPLOYEE);
        } else if (type.equalsIgnoreCase(AccountTypes.EMPLOYER.toString())) {
            userModelList = userDao.getUsersByTypeAcc(AccountTypes.EMPLOYER);
        } else {
            throw new IllegalArgumentException("Users by type " + type + " not found");
        }
        List<UserDto> dtos = new ArrayList<>();
        userModelList.forEach(e -> dtos.add(getUserDto(e)));
        return dtos;
    }

    @Override
    public UserModel getRawUserByEmail(String email) {
        return userDao.getUserByEmail(email).orElse(null);
    }

    @Override
    public List<UserDto> getEmpl(AccountTypes types) {
        List<UserModel> employees = userDao.getUsersByTypeAcc(types);
        List<UserDto> dtos = new ArrayList<>();
        employees.forEach(e -> dtos.add(getUserDto(e)));
        return dtos;
    }

    @Override
    public UserDto getEmplByEmail(String email, AccountTypes accountTypes) {
        UserModel userModel = userDao.getEmplByEmail(email, accountTypes).orElseThrow(() -> new UserNotFoundException("user by email: " + email + " not found"));
        return getUserDto(userModel);
    }

    @Override
    public UserDto getEmplByPhone(String strip, AccountTypes accountTypes) {
        UserModel userModel = userDao.getEmplByPhone(strip, accountTypes).orElseThrow(() -> new NoSuchElementException("user with phone number: " + strip + " not found"));
        return getUserDto(userModel);
    }


    private UserDto getUserDto(UserModel userModel) {
        return UserDto.builder()
                .id(userModel.getId())
                .name(userModel.getName())
                .surname(userModel.getSurname())
                .age(userModel.getAge())
                .email(userModel.getEmail())
                .phoneNumber(userModel.getPhoneNumber())
                .avatar(userModel.getAvatar())
                .accType(userDao.getRoleByUserEmail(userModel.getEmail()))
                .build();
    }
}
