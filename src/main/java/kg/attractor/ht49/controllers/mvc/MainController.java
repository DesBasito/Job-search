package kg.attractor.ht49.controllers.mvc;

import jakarta.mail.MessagingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import kg.attractor.ht49.dto.CategoryDto;
import kg.attractor.ht49.dto.RespondedApplicantDto;
import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.dto.users.PasswordDto;
import kg.attractor.ht49.dto.users.UserCreationDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.dto.vacancies.VacancyDto;
import kg.attractor.ht49.exceptions.UserNotFoundException;
import kg.attractor.ht49.services.Components.AuthAdapter;
import kg.attractor.ht49.models.UserModel;
import kg.attractor.ht49.services.interfaces.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class MainController {
    private final UserService userService;
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
        String email = adapter.getAuthUser().getEmail();
        UserDto user = userService.getUserByEmail(email);
        model.addAttribute("user", user);
        List<RespondedApplicantDto> respondedApplicants;

        if (user.getAccType().equals("employer")) {
            Page<VacancyDto> vacancies = vacancyService.getActiveVacanciesPageByEmail(page, email);
            model.addAttribute("vacancies", vacancies);
            respondedApplicants = respondedApplicantsService.getRespondentsByEmployer(user.getId());
            model.addAttribute("respondents", respondedApplicants);
            if (page - 1 < 0) {
                page = 0;
            }
            model.addAttribute("page", page);
            return "/users/companyProfile";
        } else {
            respondedApplicants = respondedApplicantsService.getRespondentsByApplicant(user.getId());
            model.addAttribute("respondents", respondedApplicants);
            Page<ResumeDto> resumes = resumeService.getResumesByAuthorEmail(email, page);
            model.addAttribute("resumes", resumes);
            if (page - 1 < 0) {
                page = 0;
            }
            model.addAttribute("page", page);
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
        userService.createUser(userCreationDto);

        try {
            request.login(userCreationDto.getEmail(), userCreationDto.getPassword());
        } catch (ServletException e) {
            log.error("Error while login ", e);
            return "redirect:/login";
        }
        return "redirect:/";
    }

    @GetMapping("forgot_password")
    public String showForgetPasswordForm(){
        return "login/forgot_password_form";
    }

    @PostMapping("/forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        try {
            userService.makeResetPasswdLink(request);
            model.addAttribute("message", "We have sent a reset password link to your email. Please check.");
        } catch (UsernameNotFoundException | UnsupportedEncodingException ex) {
            model.addAttribute("error", ex.getMessage());
        }
        catch (MessagingException ex) {
            model.addAttribute("error", "Error while sending email");
        }
        return "login/forgot_password_form";
    }

    @GetMapping("reset_password")
    public String showResetPasswordForm(@RequestParam String token,Model model){
        try {
            PasswordDto passwordDto = PasswordDto.builder().token(token).build();
            model.addAttribute("passwordDto",passwordDto);
        } catch (UsernameNotFoundException ex) {
            model.addAttribute("error", "Invalid token");
        }
        return "login/reset_password_form";
    }

    @PostMapping("/reset_password")
    public String processResetPassword(@Valid PasswordDto passwordDto,BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("passwordDto", passwordDto);
            return "login/reset_password_form";
        }
        try {
            UserModel user = userService.getByResetPasswordToken(passwordDto.getToken());
            userService.updatePassword(user, passwordDto.getPassword());
            model.addAttribute("message", "You have successfully changed your password.");
        } catch (UsernameNotFoundException ex) {
            model.addAttribute("message", "Invalid Token");
        }
        return "message";
    }
}
