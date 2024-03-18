package kg.attractor.ht49.controllers;

import kg.attractor.ht49.dto.users.UserCreationDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping()
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(service.getUsers());
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<UserDto>> getUsersByName(@PathVariable(name = "name") String name) {
        return ResponseEntity.ok(service.getUserByName(name.strip()));
    }

    @GetMapping("/phone")
    public ResponseEntity<?> getUserByPhoneNum(@RequestParam(name = "phone", defaultValue = "0") String phone) {
        UserDto user = service.getUserByPhone(phone.strip());
        return user == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with email " + phone + " not found") : ResponseEntity.ok(user);
    }

    @GetMapping("/getUsers/{type}")
    public ResponseEntity<?> getUsersByType(@PathVariable(name = "type") String type){
        try {
            return ResponseEntity.ok(service.getUsersByType(type));
        }catch (Exception e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Users by type "+type+" not found");
        }
    }


    @GetMapping("/email")
    public ResponseEntity<?> getUserByEmail(@RequestParam(name = "email", defaultValue = "example@example.com") String email) {
        UserDto user = service.getUserByEmail(email);
        return user == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with email " + email + " not found") : ResponseEntity.ok(service.getUserByEmail(email.strip()));
    }

    @GetMapping("confirm/{confirm}")
    public ResponseEntity<?> checkUserByEmail(@PathVariable String confirm){
            return ResponseEntity.ok(service.checkIfUserExists(confirm.strip()));
    }


    @PostMapping()
    public ResponseEntity<?> createUser(UserCreationDto user) {
        try {
            service.createUser(user);
            return ResponseEntity.ok(service.getUserByEmail(user.getEmail()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User is already exists");
        }

    }
}
