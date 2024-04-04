package kg.attractor.ht49.controllers.api.users;

import jakarta.validation.Valid;
import kg.attractor.ht49.dto.users.EditUserDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.services.interfaces.ProfileService;
import kg.attractor.ht49.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/profile")
public class ProfileController {
    private final ProfileService service;
    private final UserService userService;

    @PutMapping( )
    public ResponseEntity<UserDto> editProfile(@Valid EditUserDto user) {
        service.editProfile(user);
        return ResponseEntity.ok(userService.getUserById(user.getId()));
    }
}
