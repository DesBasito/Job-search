package kg.attractor.ht49.services;

import kg.attractor.ht49.dto.users.EditUserDto;
import kg.attractor.ht49.dto.users.UserCreationDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.dto.users.UserImageDto;
import kg.attractor.ht49.enums.AccountTypes;
import kg.attractor.ht49.models.User;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();
    UserDto getUserByEmail(String email) ;
    UserDto getUserById(Long id);
    void createUser(UserCreationDto user) throws Exception;
    List<UserDto> getUserByName(String name, AccountTypes type);
    UserDto getUserByPhone(String phone);
    Long getUserId(String user);
    Boolean checkIfUserExists(String email);

    void editUser(EditUserDto user);

    List<UserDto> getUsersByType(String type) throws Exception;

    void uploadImage(UserImageDto image);

    User getRawUserByEmail(String email);

    List<UserDto> getEmpl(AccountTypes types);

    UserDto getEmplByEmail(String email, AccountTypes accountTypes);

    UserDto getEmplByPhone(String strip, AccountTypes accountTypes);

}
