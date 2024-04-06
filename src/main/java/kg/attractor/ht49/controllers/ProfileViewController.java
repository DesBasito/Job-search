package kg.attractor.ht49.controllers;

import jakarta.validation.constraints.Email;
import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.dto.users.EditUserDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.dto.vacancies.VacancyDto;
import kg.attractor.ht49.enums.AccountTypes;
import kg.attractor.ht49.services.interfaces.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileViewController {
    private final ProfileService service;

    @GetMapping("/{email}")
    public String applicantInfo(Model model, @PathVariable @Email String email) {
        UserDto user = service.getUserInfoByEmail(email);
        model.addAttribute("user", user);
        if (user.getAccType().equals(AccountTypes.APPLICANT.toString())) {
            List<ResumeDto> resumes = service.getApplicantResumes(email);
            model.addAttribute("resumes", resumes);
            return "users/layoutApplicant";
        } else {
            List<VacancyDto> vacancies = service.getEmployerVacancies(email);
            model.addAttribute("vacancies", vacancies);
            return "users/layoutEmployers";
        }
    }

    @GetMapping("/edit/{id}")
    public String employerInfo(Model model, @PathVariable Long id) {
        model.addAttribute("userId", id);
        return "users/editUser";
    }

    @PostMapping("/edit")
    public String editProfile(EditUserDto userDto) {
        service.editProfile(userDto);
        return "redirect:/vacancies";
    }


}
