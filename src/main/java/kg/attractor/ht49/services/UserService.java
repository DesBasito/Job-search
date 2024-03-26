package kg.attractor.ht49.services;

import kg.attractor.ht49.dto.users.EditUserDto;
import kg.attractor.ht49.dto.users.UserCreationDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.enums.AccountTypes;
import kg.attractor.ht49.models.UserModel;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();
    UserDto getUserByEmail(String email) ;
    UserDto getUserById(Long id);
    void createUser(UserCreationDto user);
    List<UserDto> getUserByName(String name, AccountTypes type);
    UserDto getUserByPhone(String phone);
    Long getUserId(String user);

    void editUser(EditUserDto user);

    List<UserDto> getUsersByType(String type);

    UserModel getRawUserByEmail(String email);

    List<UserDto> getEmpl(Authentication autho);

    UserDto getEmplByEmail(String email, AccountTypes accountTypes);

    UserDto getEmplByPhone(String strip, AccountTypes accountTypes);

}
