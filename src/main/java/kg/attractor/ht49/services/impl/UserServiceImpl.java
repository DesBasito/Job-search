package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dto.users.EditUserDto;
import kg.attractor.ht49.dto.users.UserCreationDto;
import kg.attractor.ht49.dao.UserDao;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.enums.AccountTypes;
import kg.attractor.ht49.exceptions.AlreadyExistsException;
import kg.attractor.ht49.exceptions.UserNotFoundException;
import kg.attractor.ht49.models.UserModel;
import kg.attractor.ht49.services.interfaces.UserService;
import kg.attractor.ht49.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final FileUtil util;
    private final PasswordEncoder passwordEncoder;

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
        UserModel userModel = userDao.getUserById(id).orElseThrow(() -> new UserNotFoundException("user with id: " + id + " does not exists"));
        return getUserDto(userModel);
    }

    @Override
    public void createUser(UserCreationDto dto) {
        if (userDao.getUserByEmail(dto.getEmail()).isPresent()) {
            throw new AlreadyExistsException("User with email:" + dto.getEmail() + " already exists.");
        }
        Long roleId = userDao.getTypeIdByName(dto.getAccType());

        UserModel userModel = UserModel.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .age(dto.getAge())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .phoneNumber(dto.getPhoneNumber())
                .enabled(true)
                .avatar(null)
//                .roleId(roleId)
                .build();
        userDao.createUser(userModel);
//        userDao.createUserAuthority(getUserId(userModel.getEmail()), userModel.getRoleId());
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
    public void uploadImage(MultipartFile avatar, Authentication authentication) {
        if (avatar != null) {
            if (Objects.requireNonNull(avatar.getContentType()).matches("png|jpeg|jpg")) {
                throw new IllegalArgumentException("Unsupported img types (should be: \"png|jpeg|jpg\")");
            }
            String fileName = util.saveUploadedFile(avatar, "/images");
            userDao.setImage(fileName, authentication.getName());
        }
    }


    @Override
    public void editUser(EditUserDto user, Authentication auth) {
        UserModel userModel1 = UserModel.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .phoneNumber(user.getPhoneNumber())
                .build();
        userDao.editUser(userModel1, auth.getName());
    }

    @Override
    public void changePassword(String oldPassword, String newPassword, String email) {
        UserModel user = userDao.getUserByEmail(email).orElseThrow(() -> new UserNotFoundException("User by email: " + email + "not registered"));
        String oldPass = passwordEncoder.encode(oldPassword);
        if (user.getPassword().equals(oldPass)) {
            String pass = passwordEncoder.encode(newPassword);
            userDao.setNewPassword(pass, email);
        }
    }

    @Override
    public List<UserDto> getUsersByType(String type) {
        List<UserModel> userModelList;
        userModelList = userDao.getUsersByTypeAcc(type);
        List<UserDto> dtos = new ArrayList<>();
        userModelList.forEach(e -> dtos.add(getUserDto(e)));
        return dtos;
    }

    @Override
    public List<UserDto> getEmpl(Authentication autho) {
        String type = userDao.getRoleByUserEmail(autho.getName());
        List<UserModel> employees = userDao.getUsersByTypeAcc(type);
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

    @Override
    public ResponseEntity<InputStreamResource> downloadImage(String name) {
        return util.getOutputFile(name,"/images");
    }

    @Override
    public void login(Authentication authentication) {
        log.info("get user: "+ authentication.getPrincipal().toString());

    }

    @Override
    public Boolean loginCheck(String email, String password) {
        return userDao.getAllUsers().stream()
                .anyMatch(userModel ->
                        userModel.getEmail().equals(email) && passwordEncoder.matches(password,userModel.getPassword()));
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
