package kg.attractor.ht49.controllers.api.users;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.enums.AccountTypes;
import kg.attractor.ht49.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("restEmployee")
@RequestMapping("api/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final UserService service;

    @GetMapping()
    public ResponseEntity<List<UserDto>> getEmployees(Authentication auto) {
        return ResponseEntity.ok(service.getEmpl(auto));
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<UserDto>> getEmployeesByName(@Valid @PathVariable(name = "name") String name) {
        return ResponseEntity.ok(service.getUserByName(name.strip(), AccountTypes.EMPLOYER));
    }

    @GetMapping( "/phone/{phone}")
    public ResponseEntity<UserDto> getEmployeeByPhoneNum(@Valid @PathVariable(name = "phone") String phone) {
        UserDto user = service.getEmplByPhone(phone.strip(), AccountTypes.EMPLOYER);
        return ResponseEntity.ok(user);
    }


    @GetMapping("/email")
    public ResponseEntity<UserDto> getEmployeeByEmail(@Valid @Email @RequestParam(name = "email", defaultValue = "example@example.com") String email) {
        UserDto user = service.getEmplByEmail(email, AccountTypes.EMPLOYER);
        return ResponseEntity.ok(user);
    }
}
