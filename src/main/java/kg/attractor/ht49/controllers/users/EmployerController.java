package kg.attractor.ht49.controllers.users;

import kg.attractor.ht49.dto.RespondedApplicantDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.dto.users.UserImageDto;
import kg.attractor.ht49.enums.AccountTypes;
import kg.attractor.ht49.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
    public ResponseEntity<List<UserDto>> getEmployeesByName(@PathVariable(name = "name") String name) {
        return ResponseEntity.ok(service.getUserByName(name.strip(),AccountTypes.EMPLOYEE));
    }

    @GetMapping("/phone")
    public ResponseEntity<?> getEmployeeByPhoneNum(@RequestParam(name = "phone", defaultValue = "0") String phone) {
        UserDto user = service.getEmplByPhone(phone.strip(),AccountTypes.EMPLOYEE);
        return user == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with email " + phone + " not found") : ResponseEntity.ok(user);
    }


    @GetMapping("/email")
    public ResponseEntity<?> getEmployeeByEmail(@RequestParam(name = "email", defaultValue = "example@example.com") String email) {
        UserDto user = service.getEmplByEmail(email,AccountTypes.EMPLOYEE);
        return user == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with email " + email + " not found") : ResponseEntity.ok(service.getUserByEmail(email.strip()));
    }

    @PostMapping("/upload")
    public ResponseEntity<Void> uploadUserImage(UserImageDto image) {
        service.uploadImage(image);
        return ResponseEntity.ok().build();
    }
}
