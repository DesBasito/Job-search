package kg.attractor.ht49.controllers.users;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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


    @GetMapping("/phone")
    public ResponseEntity<?> getUserByPhoneNum(@Valid @Pattern(regexp = "^\\d+$",message = "enter a correct number") @RequestParam(name = "phone") String phone) {
        UserDto user = service.getUserByPhone(phone.strip());
        return user == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with email " + phone + " not found") : ResponseEntity.ok(user);
    }

    @GetMapping("/getUsers/{type}")
    public ResponseEntity<?> getUsersByType(@Valid@Pattern(regexp = "^[a-zA-Z]+$",message = "enter a type: 1.employee, 2.employer")@PathVariable(name = "type") String type) {
        try {
            return ResponseEntity.ok(service.getUsersByType(type));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Users by type " + type + " not found");
        }
    }


    @GetMapping("/email")
    public ResponseEntity<?> getUserByEmail(@Valid@Email @RequestParam(name = "email", defaultValue = "example@example.com") String email) {
        UserDto user = service.getUserByEmail(email);
        return user == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with email " + email + " not found") : ResponseEntity.ok(service.getUserByEmail(email.strip()));
    }


    @PostMapping()
    public ResponseEntity<UserDto> createUser(@Valid UserCreationDto user) {
        service.createUser(user);
        return ResponseEntity.ok(service.getUserByEmail(user.getEmail()));
    }
}
