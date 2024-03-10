package kg.attractor.ht49.controllers;

import kg.attractor.ht49.dto.UserDto;
import kg.attractor.ht49.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("users")
    public ResponseEntity<List<UserDto>> getUsers(){
        return ResponseEntity.ok(service.getUsers());
    }
}
