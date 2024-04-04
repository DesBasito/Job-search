package kg.attractor.ht49.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.dto.users.EditUserDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.dto.vacancies.VacancyDto;
import kg.attractor.ht49.services.interfaces.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileViewController {
    private final ProfileService service;

    @GetMapping("applicant/{email}")
    public String applicantInfo(Model model, @PathVariable @Email String email){
        UserDto user = service.getUserInfoByEmail(email);
        List<ResumeDto> resumes = service.getApplicantResumes(email);
        model.addAttribute("user",user);
        model.addAttribute("resumes",resumes);
        return "users/layoutApplicant";
    }

    @GetMapping("employer/{email}")
    public String employerInfo(Model model, @PathVariable @Email String email){
        UserDto user = service.getUserInfoByEmail(email);
        List<VacancyDto> vacancies = service.getEmployerVacancies(email);
        model.addAttribute("user",user);
        model.addAttribute("vacancies",vacancies);
        return "users/layoutEmployers";
    }

    @PutMapping()
    public ResponseEntity<UserDto> editProfile(@Valid EditUserDto user, Authentication authentication) {
        service.editProfile(user);
        return ResponseEntity.ok(service.getUserInfoByEmail(authentication.getName()));
    }
}
