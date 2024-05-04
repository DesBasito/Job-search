package kg.attractor.ht49.services.interfaces;

import kg.attractor.ht49.dto.users.EditUserDto;
import kg.attractor.ht49.dto.users.UserCreationDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.models.UserModel;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    UserDto getUser(String email);
    UserDto getUserByEmail(String email) ;
    UserDto getUserById(Long id);
    void createUser(UserCreationDto user);
    UserDto getUserByPhone(String phone);
    Long getUserId(String user);

    void uploadImage(MultipartFile avatar, Authentication authentication);

    void editUser(EditUserDto user, Authentication auth);

    void changePassword(String oldPassword, String newPassword, String email);

    ResponseEntity<InputStreamResource> downloadImage(String name);


    Boolean loginCheck(String email, String password);

    UserModel getUserModelByEmail(String email);
}
