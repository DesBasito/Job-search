package kg.attractor.ht49.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import kg.attractor.ht49.dto.CategoryDto;
import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.dto.users.UserCreationDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.dto.vacancies.VacancyDto;
import kg.attractor.ht49.exceptions.UserNotFoundException;
import kg.attractor.ht49.AuthAdapter;
import kg.attractor.ht49.services.interfaces.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
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
            if (accType.equals("employer")) {
                return "resume/resumeList";
            } else {
                return "main/main";
            }
        } catch (UserNotFoundException | IllegalArgumentException e) {
            return "main/main";
        }
    }

    @GetMapping("/profile")
    public String applicantInfo(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        if (page - 1 < 1) {
            page = 0;
        }
        String email = adapter.getAuthUser().getEmail();
        UserDto user = service.getUserByEmail(email);
        model.addAttribute("user", user);
        model.addAttribute("page", page);

        if (user.getAccType().equals("employer")) {
            Page<VacancyDto> vacancies = vacancyService.getActiveVacanciesPageByEmail(page, email);
            model.addAttribute("vacancies", vacancies);
            Integer size = respondedApplicantsService.getRespondentsSizeByEmployer(user.getEmail());
            model.addAttribute("respondents", size);
            return "/users/companyProfile";
        } else {
            Page<ResumeDto> resumes = resumeService.getResumesByAuthorEmail(email, page);
            model.addAttribute("resumes", resumes);
            return "/users/profile";
        }
    }


    @GetMapping("/register")
    public String create(Model model) {
        UserCreationDto userCreationDto = new UserCreationDto();
        model.addAttribute("userCreationDto", userCreationDto);
        return "login/register";
    }


    @PostMapping("/register")
    public String create(@Valid UserCreationDto userCreationDto, BindingResult bindingResult, Model model, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userCreationDto", userCreationDto);
            return "login/register";
        }
        service.createUser(userCreationDto);
        try {
            request.login(userCreationDto.getEmail(), userCreationDto.getPassword());
        } catch (ServletException e) {
            log.error("Error while login ", e);
            return "redirect:/login";
        }
        return "redirect:/";

    }
}
