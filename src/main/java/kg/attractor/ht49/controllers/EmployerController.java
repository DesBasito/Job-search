package kg.attractor.ht49.controllers;

import jakarta.validation.constraints.Email;
import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.dto.users.EditUserDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.dto.vacancies.VacancyDto;
import kg.attractor.ht49.services.interfaces.UserService;
import kg.attractor.ht49.services.interfaces.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("employer")
@RequiredArgsConstructor
public class EmployerController {
    private final UserService service;
    private final VacancyService vacancyService;

//    @GetMapping("profile")
//    public String applicantInfo(Model model, Authentication authentication) {
//        UserDto user = service.getUserByEmail(authentication.getName());
//        model.addAttribute("user", user);
//        List<VacancyDto> vacancies = vacancyService.getAllVacanciesByCompany(user.getId());
//        model.addAttribute("vacancies", vacancies);
//        return "users/layoutEmployers";
//    }
//
//    @GetMapping("/edit")
//    public String employerInfo(Model model, Authentication authentication) {
//        UserDto user = service.getUserByEmail(authentication.getName());
//        model.addAttribute("accType",user.getAccType());
//        return "users/editUser";
//    }
//
//    @PostMapping("/edit")
//    public String editProfile(EditUserDto userDto, Authentication auth) {
//        service.editUser(userDto,auth);
//        return "redirect:/vacancies";
//    }

    @GetMapping("profile/{email}")
    public String employerInfo(Model model, @PathVariable String email) {
        UserDto user = service.getUserByEmail(email);
        model.addAttribute("user", user);
        List<VacancyDto> vacancies = vacancyService.getAllVacanciesByCompany(user.getId());
        model.addAttribute("resumes", vacancies);
        return "users/layoutEmployers";
    }
    @GetMapping("/edit/{email}")
    public String editProfile(Model model, @PathVariable @Email String email) {
        UserDto user = service.getUserByEmail(email);
        model.addAttribute("accType", user.getAccType());
        return "users/editUser";
    }

    @PostMapping("/edit")
    public String editProfile(EditUserDto userDto, String email) {
        service.editUserTest(userDto,email);
        return "redirect:/vacancies";
    }

}
