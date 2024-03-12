package kg.attractor.ht49.services;

import kg.attractor.ht49.dto.UserDto;
import kg.attractor.ht49.exceptions.CategoryNotFoundException;
import kg.attractor.ht49.exceptions.UserNotFoundException;
import kg.attractor.ht49.models.User;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();
    UserDto getUserByEmail(String email) throws UserNotFoundException;
    void createUser(User user);
    List<UserDto> getUserByName(String name);
    UserDto getUserByPhone(String phone) throws UserNotFoundException;
    Long getUserId(String user) throws UserNotFoundException;
    Boolean checkIfUserExists(String email);

    List<UserDto> getAllUsersByVacancyId(Long id);
}
