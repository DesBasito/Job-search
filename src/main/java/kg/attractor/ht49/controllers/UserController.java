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
@RequestMapping("users")
public class UserController {
    private final UserService service;

    @GetMapping()
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(service.getUsers());
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<UserDto>> getUsersByName(@PathVariable String name) {
        return ResponseEntity.ok(service.getUserByName(name.strip()));
    }

    @GetMapping("/phone")
    public ResponseEntity<?> getUserByPhoneNum(@RequestParam(name = "phone", defaultValue = "0") String phone) {
        try {
            UserDto user = service.getUserByPhone(phone.strip());
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/email")
    public ResponseEntity<?> getUserByEmail(@RequestParam(name = "email", defaultValue = "example@example.com") String email) {
        try {
            return ResponseEntity.ok(service.getUserByEmail(email.strip()));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/confirm/{email}")
    public ResponseEntity<?> checkUserByEmail(@PathVariable String email){
       String userEmail = email.strip();
            return ResponseEntity.ok(service.checkIfUserExists(userEmail));
    }


    @PostMapping("users")
    public HttpStatus createUser(User user) {
        service.createUser(user);
        return HttpStatus.OK;
    }
}
