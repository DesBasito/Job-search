package kg.attractor.ht49.controllers;

import kg.attractor.ht49.dto.CategoryDto;
import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.dto.users.UserCreationDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.dto.vacancies.VacancyDto;
import kg.attractor.ht49.services.interfaces.CategoryService;
import kg.attractor.ht49.services.interfaces.ResumeService;
import kg.attractor.ht49.services.interfaces.UserService;
import kg.attractor.ht49.services.interfaces.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class MainController {
    private final UserService service;
    private final ResumeService resumeService;
    private final VacancyService vacancyService;
    private final CategoryService categoryService;
    @GetMapping("/login")
    public String login() {
        return "login/login";
    }

    @GetMapping()
    public String getVacancies(Model model, @RequestParam(name = "page", defaultValue = "0") Integer page){
        if (page < 0){
            page = 0;
        }
        model.addAttribute("page", page);
        Page<VacancyDto> vacancies = vacancyService.getActiveVacanciesPage(page);
        model.addAttribute("vacancies",vacancies);
        List<CategoryDto> categories = categoryService.getCategories();
        model.addAttribute("categories",categories);
        return "main/main";
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

    @GetMapping("/register")
    public String create() {
        return "login/register";
    }


    @PostMapping("/register")
    public String create(UserCreationDto newUser) {
        service.createUser(newUser);
        return "redirect:/";
    }
}
