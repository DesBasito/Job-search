package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dao.UserDao;
import kg.attractor.ht49.dto.UserDto;
import kg.attractor.ht49.exceptions.UserNotFoundException;
import kg.attractor.ht49.models.User;
import kg.attractor.ht49.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userDao.getAllUsers();
        List<UserDto> dtos = new ArrayList<>();
        users.forEach(e -> dtos.add(UserDto.builder()
                .id(e.getId())
                .name(e.getName())
                .surname(e.getSurname())
                .age(e.getAge())
                .email(e.getEmail())
                .phoneNumber(e.getPhoneNumber())
                .avatar(e.getAvatar())
                .accType(e.getAccType())
                .build()));
        return dtos;
    }

    @Override
    public UserDto getUserById(int id) throws UserNotFoundException {
        User user = userDao.getUserById(id).orElseThrow(() -> new UserNotFoundException("cannot find user with this ID: "+id));
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
