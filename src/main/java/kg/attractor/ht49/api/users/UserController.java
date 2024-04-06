package kg.attractor.ht49.api.users;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import kg.attractor.ht49.dto.users.UserCreationDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("restProfile")
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping()
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(service.getUsers());
    }


    @GetMapping("/phone")
    public ResponseEntity<UserDto> getUserByPhoneNum(@Valid @Pattern(regexp = "^\\d+$",message = "enter a correct number") @RequestParam(name = "phone") String phone) {
        UserDto user = service.getUserByPhone(phone.strip());
        return ResponseEntity.ok(user);
    }

    @GetMapping("/getByType/{type}")
    public ResponseEntity<List<UserDto>> getUsersByType(@Valid@Pattern(regexp = "^[a-zA-Z]+$",message = "enter a type: 1.employee, 2.employer")@PathVariable(name = "type") String type) {
            return ResponseEntity.ok(service.getUsersByType(type));
    }


    @GetMapping("/email")
    public ResponseEntity<UserDto> getUserByEmail(@Valid@Email @RequestParam(name = "email", defaultValue = "example@example.com") String email) {
        UserDto user = service.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }


    @PostMapping()
    public ResponseEntity<UserCreationDto> createUser(@Valid UserCreationDto user) {
        service.createUser(user);
        return ResponseEntity.ok(user);
    }
}
