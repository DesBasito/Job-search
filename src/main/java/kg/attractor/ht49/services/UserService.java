package kg.attractor.ht49.services;

import kg.attractor.ht49.dto.UserDto;
import kg.attractor.ht49.exceptions.UserNotFoundException;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();

    UserDto getUserById(int id) throws UserNotFoundException;
}
