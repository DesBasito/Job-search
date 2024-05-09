package kg.attractor.ht49.controllers;

import kg.attractor.ht49.dto.CategoryDto;
import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.dto.users.UserCreationDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.dto.vacancies.VacancyDto;
import kg.attractor.ht49.exceptions.UserNotFoundException;
import kg.attractor.ht49.services.AuthAdapter;
import kg.attractor.ht49.services.interfaces.*;
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
    private final AuthAdapter adapter;
    private final RespondedApplicantsService respondedApplicantsService;

    @GetMapping("/login")
    public String login() {
        return "login/login";
    }

    @GetMapping()
    public String getMainPage(Model model, @RequestParam(name = "filter", defaultValue = "null") String filter) {
            List<CategoryDto> categories = categoryService.getCategories();
            model.addAttribute("categories", categories);
            model.addAttribute("filter", filter);
        try {
            String accType = adapter.getAuthUser().getAccType();
            if (accType.equals("employer")){
                return "resume/resumeList";
            }else {
                return "main/main";
            }
        } catch (UserNotFoundException | IllegalArgumentException e) {
            return "main/main";
        }
    }

    @GetMapping("/profile")
    public String applicantInfo(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        if (page - 1 <= -1) {
            page = 0;
        }
        String email = adapter.getAuthUser().getEmail();
        UserDto user = service.getUserByEmail(email);
        model.addAttribute("user", user);
        Page<ResumeDto> resumes = resumeService.getResumesByAuthorEmail(email, page);
        Page<VacancyDto> vacancies = vacancyService.getActiveVacanciesPageByEmail(page, email);
        Integer size = respondedApplicantsService.getRespondentsSizeByEmployer(user.getEmail());
        model.addAttribute("resumes", resumes);
        model.addAttribute("vacancies", vacancies);
        model.addAttribute("respondents", size);
        model.addAttribute("page", page);
        return "/users/profile";
    }


    @GetMapping("/register")
    public String create() {
        return "login/register";
    }


    @PostMapping("/register")
    public String create(UserCreationDto newUser) {
        service.createUser(newUser);
        return "redirect:/login";
    }
}
