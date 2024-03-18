package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dto.UserCreationDto;
import kg.attractor.ht49.dao.UserDao;
import kg.attractor.ht49.dto.UserDto;
import kg.attractor.ht49.enums.AccountTypes;
import kg.attractor.ht49.exceptions.UserNotFoundException;
import kg.attractor.ht49.models.User;
import kg.attractor.ht49.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userDao.getAllUsers();
        List<UserDto> dtos = new ArrayList<>();
        users.forEach(e -> dtos.add(getUserDto(e)));
        return dtos;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        try {
            User user = userDao.getUserByEmail(email).orElseThrow(UserNotFoundException::new);
            return getUserDto(user);
        } catch (UserNotFoundException e) {
            log.error("user with email: {} does not exists", email);
        }
        return null;
    }


    @Override
    public UserDto getUserById(Long id) {
        try {
            User user = userDao.getUserById(id).orElseThrow(UserNotFoundException::new);
            return getUserDto(user);
        } catch (UserNotFoundException e) {
            log.error("User with {} not found", id);
        }
        return null;
    }

    @Override
    public void createUser(UserCreationDto user) throws Exception {
        if (userDao.getUserByEmail(user.getEmail()).isPresent()){
            throw new Exception();
        }
        userDao.createUser(user);
    }

    @Override
    public List<UserDto> getUserByName(String name) {
        List<User> users = userDao.getUserByName(name);
        List<UserDto> dtos = new ArrayList<>();
        users.forEach(e -> dtos.add(getUserDto(e)));
        return dtos;
    }

    @Override
    public UserDto getUserByPhone(String phone) {
        try {
            User user = userDao.getUserByPhone(phone).orElseThrow(UserNotFoundException::new);
            return getUserDto(user);

        } catch (UserNotFoundException e) {
            log.error("cannot find user with phone number: {}", phone);
        }
        return null;
    }

    @Override
    public Long getUserId(String email) {
        try {
            User user1 = userDao.getUserByEmail(email).orElseThrow(UserNotFoundException::new);
            return user1.getId();
        } catch (UserNotFoundException e) {
            log.error("user with email: {} does not exists", email);
        }
        return null;
    }

    @Override
    public Boolean checkIfUserExists(String email) {
        return userDao.getUserByEmail(email).isPresent();
    }

    @Override
    public void editUser(User user) {
        userDao.editUser(user);
    }

    @Override
    public List<UserDto> getUsersByType(String type) throws Exception {
        List<User> userList;
        if (type.equalsIgnoreCase(AccountTypes.EMPLOYEE.toString())){
            userList =  userDao.getEmployees(AccountTypes.EMPLOYEE);
        }else if(type.equalsIgnoreCase(AccountTypes.EMPLOYER.toString())){
            userList = userDao.getEmployees(AccountTypes.EMPLOYER);
        }else {
            log.error("Users by type {} not found",type);
            throw new Exception();
        }
        List<UserDto> dtos = new ArrayList<>();
        userList.forEach(e -> dtos.add(getUserDto(e)));
        return dtos;
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
