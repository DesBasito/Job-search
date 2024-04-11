package kg.attractor.ht49.controllers;

import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.dto.users.UserCreationDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.dto.vacancies.VacancyDto;
import kg.attractor.ht49.services.interfaces.ResumeService;
import kg.attractor.ht49.services.interfaces.UserService;
import kg.attractor.ht49.services.interfaces.VacancyService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class MainController {
    private final UserService service;
    private final ResumeService resumeService;
    private final VacancyService vacancyService;
    @GetMapping("login")
    public String login() {
        return "login/login";
    }

    @GetMapping("/profile")
    public String applicantInfo(Model model, Authentication authentication) {
        UserDto user = service.getUserByEmail(authentication.getName());
        model.addAttribute("user", user);
        List<ResumeDto> resumes = resumeService.getResumesByUserEmail(authentication.getName());
        List<VacancyDto> vacancies = vacancyService.getAllVacanciesByCompany(authentication.getName());
        model.addAttribute("resumes", resumes);
        model.addAttribute("vacancies", vacancies);
        return "/users/profile";
    }

    @GetMapping("register")
    public String create() {
        return "login/register";
    }


    @PostMapping("register")
    public String create(UserCreationDto newUser, Model model) {
        service.createUser(newUser);
        return passToProfile(newUser, model);
    }

    @NotNull
    private String passToProfile(UserCreationDto newUser, Model model) {
        UserDto user = service.getUserByEmail(newUser.getEmail());
        model.addAttribute("user", user);
        List<ResumeDto> resumes = resumeService.getResumesByUserEmail(newUser.getEmail());
        List<VacancyDto> vacancies = vacancyService.getAllVacanciesByCompany(newUser.getEmail());
        model.addAttribute("resumes", resumes);
        model.addAttribute("vacancies", vacancies);

        return "redirect:/profile";
    }

    @GetMapping("scriptTask")
    public String scriptTask() {
        return "main/script";
    }
}
