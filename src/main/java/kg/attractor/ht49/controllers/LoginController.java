package kg.attractor.ht49.controllers;

import kg.attractor.ht49.dto.users.UserCreationDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class LoginController {
    private final UserService service;

    @GetMapping("register")
    public String create() {
        return "login/register";
    }

    @GetMapping("login")
    public String login() {
        return "login/login";
    }

    @PostMapping("register")
    public String create(UserCreationDto newUser, Model model) {
        service.createUser(newUser);
        UserDto user = service.getUserByEmail(newUser.getEmail());
        model.addAttribute("user", user);
        return "redirect:/vacancies";

    }

    @PostMapping("login")
    public String login(UserCreationDto newUser, Model model) {
        service.createUser(newUser);
        UserDto user = service.getUserByEmail(newUser.getEmail());
        model.addAttribute("user", user);
        return "redirect:/vacancies";
    }
}