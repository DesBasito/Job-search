package kg.attractor.ht49.controllers;

import kg.attractor.ht49.models.User;
import kg.attractor.ht49.services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("profile")
public class ProfileController {
    private final ProfileService service;

    @PostMapping("/edit")
    public HttpStatus editProfile(@RequestBody User user) {
        service.editProfile(user);
        return HttpStatus.OK;
    }
}
