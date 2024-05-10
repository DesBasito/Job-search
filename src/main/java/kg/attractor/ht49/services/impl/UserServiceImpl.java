package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dto.users.EditUserDto;
import kg.attractor.ht49.dto.users.UserCreationDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.exceptions.AlreadyExistsException;
import kg.attractor.ht49.exceptions.UserNotFoundException;
import kg.attractor.ht49.models.Authority;
import kg.attractor.ht49.models.UserModel;
import kg.attractor.ht49.repositories.AuthorityRepository;
import kg.attractor.ht49.repositories.UserModelRepository;
import kg.attractor.ht49.services.interfaces.UserService;
import kg.attractor.ht49.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.NoSuchElementException;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserModelRepository userModelRepository;
    private final AuthorityRepository authorityRepository;
    private final FileUtil util;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto getUser(String email) {
        UserModel userModel = userModelRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        String accType = getAccTypeByUserEmail(email);
        return getUserDto(userModel,accType);
    }

    private String getAccTypeByUserEmail(String email) {
        UserModel user = userModelRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        return user.getRole().getRole();
    }

    @Override
    public UserDto getUserByEmail(String email) {
        String accType = getAccTypeByUserEmail(email);
        return getUserDto(userModelRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("user with email: " + email + " does not exists")), accType);
    }


    @Override
    public UserDto getUserById(Long id) {
        UserModel user = userModelRepository.findById(id).orElseThrow(() -> new UserNotFoundException("user with id: " + id + " does not exists"));
        String accType = getAccTypeByUserEmail(user.getEmail());
        return getUserDto(user, accType);
    }

    @Override
    public void createUser(UserCreationDto dto) {
        if (userModelRepository.existsByEmail(dto.getEmail())) {
            throw new AlreadyExistsException("User with email:" + dto.getEmail() + " already exists.");
        }
        Authority authority = authorityRepository.findById(dto.getAccType()).orElseThrow(() ->new NoSuchElementException("authority not found"));
        UserModel userModel = UserModel.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .age(dto.getAge())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .phoneNumber(dto.getPhoneNumber())
                .enabled(true)
                .role(authority)
                .avatar(null)
                .build();
        userModelRepository.save(userModel);
    }

    @Override
    public UserDto getUserByPhone(String phone) {
        UserModel userModel = userModelRepository.findByPhoneNumber(phone).orElseThrow(() -> new UserNotFoundException("User by phone num: " + phone + " not found"));
        String accType = getAccTypeByUserEmail(userModel.getEmail());
        return getUserDto(userModel,accType);
    }

    @Override
    public Long getUserId(String email) {
        UserModel userModel1 = userModelRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User by email " + email + " not found"));
        return userModel1.getId();
    }

    @Override
    public void uploadImage(MultipartFile avatar, String email) {
        if (avatar != null) {
            if (Objects.requireNonNull(avatar.getContentType()).matches("png|jpeg|jpg")) {
                throw new IllegalArgumentException("Unsupported img types (should be: \"png|jpeg|jpg\")");
            }
            String fileName = util.saveUploadedFile(avatar, "/images");
            UserModel user = userModelRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User by email " + email + " not found"));
            user.setAvatar(fileName);
            userModelRepository.save(user);
        }
    }


    @Override
    public void editUser(EditUserDto user, String email) {
        UserModel userModel = userModelRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User by email " + email + " not found"));
        userModel.setName(user.getName());
        userModel.setSurname(user.getSurname());
        userModel.setPhoneNumber(user.getPhoneNumber());
        userModel.setAge(user.getAge());
        userModelRepository.save(userModel);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword, String email) {
        UserModel user = userModelRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User by email: " + email + "not registered"));
        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userModelRepository.save(user);
        }
    }


    @Override
    public ResponseEntity<InputStreamResource> downloadImage(String name) {
        return util.getOutputFile(name, "/images");
    }


    @Override
    public Boolean loginCheck(String email, String password) {
        return userModelRepository.findAll().stream()
                .anyMatch(userModel ->
                        userModel.getEmail().equals(email) && passwordEncoder.matches(password, userModel.getPassword()));
    }

    @Override
    public UserModel getUserModelByEmail(String email) {
        return userModelRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User by email " + email + " not found"));
    }


    private UserDto getUserDto(UserModel userModel, String accType) {
        return UserDto.builder()
                .id(userModel.getId())
                .name(userModel.getName())
                .surname(userModel.getSurname())
                .age(userModel.getAge())
                .accType(accType)
                .email(userModel.getEmail())
                .phoneNumber(userModel.getPhoneNumber())
                .avatar(userModel.getAvatar())
                .build();
    }
}
