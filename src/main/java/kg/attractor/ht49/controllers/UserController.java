package kg.attractor.ht49.controllers;

import kg.attractor.ht49.dto.UserDto;
import kg.attractor.ht49.exceptions.UserNotFoundException;
import kg.attractor.ht49.models.User;
import kg.attractor.ht49.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(service.getUsers());
    }

    @GetMapping("users/{name}")
    public ResponseEntity<List<UserDto>> getUsersByName(@PathVariable(name = "name") String name) {
        return ResponseEntity.ok(service.getUserByName(name.strip()));
    }

    @GetMapping("users/phone")
    public ResponseEntity<?> getUserByPhoneNum(@RequestParam(name = "phone", defaultValue = "0") String phone) {
        try {
            UserDto user = service.getUserByPhone(phone.strip());
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("users/email")
    public ResponseEntity<?> getUserByEmail(@RequestParam(name = "email", defaultValue = "example@example.com") String email) {
        UserDto user = service.getUserByEmail(email);
        return user == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with email " + email + " not found") : ResponseEntity.ok(service.getUserByEmail(email.strip()));
    }

    @GetMapping("users/confirm")
    public ResponseEntity<?> checkUserByEmail(@RequestParam(name = "userEmail",defaultValue = "")String email){
            return ResponseEntity.ok(service.checkIfUserExists(email.strip()));
    }


    @PostMapping("users")
    public HttpStatus createUser(User user) {
        service.createUser(user);
        return HttpStatus.OK;
    }
}
