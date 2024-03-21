package kg.attractor.ht49.controllers.users;

import kg.attractor.ht49.dto.users.EditUserDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.models.User;
import kg.attractor.ht49.services.ProfileService;
import kg.attractor.ht49.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("profile")
public class ProfileController {
    private final ProfileService service;
    private final UserService userService;

    @PutMapping("/edit")
    public ResponseEntity<UserDto> editProfile(EditUserDto user) {
        service.editProfile(user);
        return ResponseEntity.ok(userService.getUserById(user.getId()));
    }
}
