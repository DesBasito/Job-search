package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dto.users.EditUserDto;
import kg.attractor.ht49.dto.users.UserCreationDto;
import kg.attractor.ht49.dao.UserDao;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.dto.users.UserImageDto;
import kg.attractor.ht49.enums.AccountTypes;
import kg.attractor.ht49.exceptions.UserNotFoundException;
import kg.attractor.ht49.models.User;
import kg.attractor.ht49.models.UserImages;
import kg.attractor.ht49.services.UserService;
import kg.attractor.ht49.utils.FileUtil;
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
    public List<UserDto> getUserByName(String name,AccountTypes type) {
        List<User> users = userDao.getUserByName(name,type);
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
    public void editUser(EditUserDto user) {
        User user1 = User.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .avatar(user.getAvatar())
                .build();
        userDao.editUser(user1);
    }

    @Override
    public List<UserDto> getUsersByType(String type) throws Exception {
        List<User> userList;
        if (type.equalsIgnoreCase(AccountTypes.EMPLOYEE.toString())){
            userList =  userDao.getUsersByTypeAcc(AccountTypes.EMPLOYEE);
        }else if(type.equalsIgnoreCase(AccountTypes.EMPLOYER.toString())){
            userList = userDao.getUsersByTypeAcc(AccountTypes.EMPLOYER);
        }else {
            log.error("Users by type {} not found",type);
            throw new Exception();
        }
        List<UserDto> dtos = new ArrayList<>();
        userList.forEach(e -> dtos.add(getUserDto(e)));
        return dtos;
    }

    @Override
    public void uploadImage(UserImageDto image) {
        String fileName  = util.saveUploadedFile(image.getFile(),"/images");
        try {
            User user = userDao.getUserById(image.getUserId()).orElseThrow(UserNotFoundException::new);
            UserImages userImages = UserImages.builder()
                    .userId(image.getUserId())
                    .fileName(fileName)
                    .build();
            userDao.uploadUserImages(userImages);
            user.setAvatar(fileName);
            userDao.editUser(user);
        }catch (UserNotFoundException e){
            log.error("User by id {} not found",image.getUserId());
        }
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
        try {
            User user = userDao.getEmplByEmail(email,accountTypes).orElseThrow(UserNotFoundException::new);
            return getUserDto(user);
        } catch (UserNotFoundException e) {
            log.error("employee with email: {} does not exists", email);
        }
        return null;
    }

    @Override
    public UserDto getEmplByPhone(String strip, AccountTypes accountTypes) {
        try {
            User user = userDao.getEmplByPhone(strip,accountTypes).orElseThrow(UserNotFoundException::new);
            return getUserDto(user);
        } catch (UserNotFoundException e) {
            log.error("employee with phone: {} does not exists", strip);
        }
        return null;
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
