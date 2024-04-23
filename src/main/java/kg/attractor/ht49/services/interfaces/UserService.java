package kg.attractor.ht49.services.interfaces;

import kg.attractor.ht49.dto.users.EditUserDto;
import kg.attractor.ht49.dto.users.UserCreationDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.enums.AccountTypes;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();
    UserDto getUserByEmail(String email) ;
    UserDto getUserById(Long id);
    void createUser(UserCreationDto user);
    List<UserDto> getUserByName(String name, AccountTypes type);
    UserDto getUserByPhone(String phone);
    Long getUserId(String user);

    void uploadImage(MultipartFile avatar, Authentication authentication);

    void editUser(EditUserDto user, Authentication auth);

    void changePassword(String oldPassword, String newPassword, String email);

    List<UserDto> getUsersByType(String type);

    List<UserDto> getEmpl(Authentication autho);

    UserDto getEmplByEmail(String email, AccountTypes accountTypes);

    UserDto getEmplByPhone(String strip, AccountTypes accountTypes);

    ResponseEntity<InputStreamResource> downloadImage(String name);

    void login(Authentication authentication);
}
