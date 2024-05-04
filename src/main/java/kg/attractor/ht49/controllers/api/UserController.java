package kg.attractor.ht49.controllers.api;

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
    public ResponseEntity<UserDto> getUser(@RequestParam String email) {
        return ResponseEntity.ok(service.getUser(email));
    }


    @GetMapping("/phone")
    public ResponseEntity<UserDto> getUserByPhoneNum(@Valid @Pattern(regexp = "^\\d+$",message = "enter a correct number") @RequestParam(name = "phone") String phone) {
        UserDto user = service.getUserByPhone(phone.strip());
        return ResponseEntity.ok(user);
    }



    @GetMapping("/email")
    public ResponseEntity<UserDto> getUserByEmail(@Valid@Email @RequestParam(name = "email", defaultValue = "example@example.com") String email) {
        UserDto user = service.getUserByEmail(email);
        return ResponseEntity.ok(user);
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
