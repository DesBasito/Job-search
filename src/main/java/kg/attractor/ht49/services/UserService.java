package kg.attractor.ht49.services;

import kg.attractor.ht49.dto.UserCreationDto;
import kg.attractor.ht49.dto.UserDto;
import kg.attractor.ht49.models.User;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();
    UserDto getUserByEmail(String email) ;
    UserDto getUserById(Long id);
    void createUser(UserCreationDto user) throws Exception;
    List<UserDto> getUserByName(String name);
    UserDto getUserByPhone(String phone);
    Long getUserId(String user);
    Boolean checkIfUserExists(String email);

    void editUser(User user);
}
