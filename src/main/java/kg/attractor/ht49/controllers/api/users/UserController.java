package kg.attractor.ht49.controllers.api.users;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import kg.attractor.ht49.dto.users.UserCreationDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.services.interfaces.UserService;
import kg.attractor.ht49.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("restUser")
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

    @PostMapping("/login")
    public HttpStatus login(Authentication authentication) {
        if (authentication == null) {
                return HttpStatus.CONFLICT;
        }
        service.login(authentication);
        return HttpStatus.OK;
    }

    @GetMapping("/checkLogin")
    public ResponseEntity<Boolean> loginCheck(@RequestParam String email, @RequestParam String password) {
        boolean isValidLogin = service.loginCheck(email, password);
        return ResponseEntity.ok(isValidLogin);
    }

    @PostMapping()
    public ResponseEntity<UserCreationDto> createUser(@Valid UserCreationDto user) {
        service.createUser(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "image/{name}")
    public ResponseEntity<InputStreamResource> get(@PathVariable  String name){
        return service.downloadImage(name);
    }
}
