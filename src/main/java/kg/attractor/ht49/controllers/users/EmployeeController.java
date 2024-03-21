package kg.attractor.ht49.controllers.users;

import jakarta.validation.constraints.NotBlank;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.enums.AccountTypes;
import kg.attractor.ht49.exceptions.UserNotFoundException;
import kg.attractor.ht49.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final UserService service;

    @GetMapping()
    public ResponseEntity<List<UserDto>> getEmployees() {
        return ResponseEntity.ok(service.getEmpl(AccountTypes.EMPLOYER));
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<UserDto>> getEmployeesByName(@PathVariable(name = "name") String name) {
        return ResponseEntity.ok(service.getUserByName(name.strip(), AccountTypes.EMPLOYER));
    }

    @GetMapping( "/phone/{phone}")
    public ResponseEntity<UserDto> getEmployeeByPhoneNum(@NotBlank @PathVariable(name = "phone") String phone) {
        UserDto user = service.getEmplByPhone(phone.strip(), AccountTypes.EMPLOYER);
        return ResponseEntity.ok(user);
    }


    @GetMapping("/email")
    public ResponseEntity<UserDto> getEmployeeByEmail(@RequestParam(name = "email", defaultValue = "example@example.com") String email) {
        UserDto user = service.getEmplByEmail(email, AccountTypes.EMPLOYER);
        return ResponseEntity.ok(user);
    }
}
