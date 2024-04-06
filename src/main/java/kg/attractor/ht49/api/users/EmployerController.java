package kg.attractor.ht49.api.users;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.enums.AccountTypes;
import kg.attractor.ht49.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("restEmployer")
@RequestMapping("api/employer")
@RequiredArgsConstructor
public class EmployerController {
    private final UserService service;
    @GetMapping()
    public ResponseEntity<List<UserDto>> getEmployees(Authentication autho){
        return ResponseEntity.ok(service.getEmpl(autho));
    }
    @GetMapping("/{name}")
    public ResponseEntity<List<UserDto>> getEmployeesByName(@Valid @Pattern(regexp = "^[a-zA-Z]+$", message = "Input should contain only letters") @PathVariable(name = "name") String name) {
        return ResponseEntity.ok(service.getUserByName(name.strip(),AccountTypes.APPLICANT));
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<UserDto> getEmployeeByPhoneNum(@Valid @NotBlank @PathVariable(name = "phone") String phone) {
        UserDto user = service.getEmplByPhone(phone.strip(),AccountTypes.APPLICANT);
        return ResponseEntity.ok(user);
    }


    @GetMapping("/email")
    public ResponseEntity<UserDto> getEmployeeByEmail(@Valid @Email @RequestParam(name = "email", defaultValue = "example@example.com") String email) {
        UserDto user = service.getEmplByEmail(email,AccountTypes.APPLICANT);
        return ResponseEntity.ok(user);
    }

}
