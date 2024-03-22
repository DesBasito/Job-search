package kg.attractor.ht49.controllers.users;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.enums.AccountTypes;
import kg.attractor.ht49.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employer")
@RequiredArgsConstructor
public class EmployerController {
    private final UserService service;
    @GetMapping()
    public ResponseEntity<List<UserDto>> getEmployees(){
        return ResponseEntity.ok(service.getEmpl(AccountTypes.EMPLOYEE));
    }
    @GetMapping("/{name}")
    public ResponseEntity<List<UserDto>> getEmployeesByName(@Valid @Pattern(regexp = "^[a-zA-Z]+$", message = "Input should contain only letters") @PathVariable(name = "name") String name) {
        return ResponseEntity.ok(service.getUserByName(name.strip(),AccountTypes.EMPLOYEE));
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<UserDto> getEmployeeByPhoneNum(@Valid @NotBlank @PathVariable(name = "phone") String phone) {
        UserDto user = service.getEmplByPhone(phone.strip(),AccountTypes.EMPLOYEE);
        return ResponseEntity.ok(user);
    }


    @GetMapping("/email")
    public ResponseEntity<UserDto> getEmployeeByEmail(@Valid @Email @RequestParam(name = "email", defaultValue = "example@example.com") String email) {
        UserDto user = service.getEmplByEmail(email,AccountTypes.EMPLOYEE);
        return ResponseEntity.ok(user);
    }

}
