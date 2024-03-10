package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dao.UserDao;
import kg.attractor.ht49.dto.UserDto;
import kg.attractor.ht49.models.User;
import kg.attractor.ht49.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao dao;

    @Override
    public List<UserDto> getUsers() {
        List<User> users = dao.getAllUsers();
        List<UserDto> dtos = new ArrayList<>();
        users.forEach(e -> UserDto.builder()
                .id(e.getId())
                .name(e.getName())
                .surname(e.getSurname())
                .age(e.getAge())
                .email(e.getEmail())
                .password(e.getPassword())
                .phoneNumber(e.getPhoneNumber())
                .avtar(e.getAvtar())
                .accType(e.getAccType())
                .build());
        return dtos;
    }
}
