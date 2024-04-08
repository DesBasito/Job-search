package kg.attractor.ht49.controllers;

import jakarta.validation.constraints.Email;
import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.dto.users.EditUserDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.services.interfaces.ResumeService;
import kg.attractor.ht49.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("applicant")
@RequiredArgsConstructor
public class ApplicantController {
    private final UserService service;
    private final ResumeService resumeService;

    @PreAuthorize("hasAuthority('employee')")
    @GetMapping("/profile")
    public String applicantInfo(Model model, Authentication authentication) {
        UserDto user = service.getUserByEmail(authentication.getName());
        model.addAttribute("user", user);
        List<ResumeDto> resumes = resumeService.getResumesByUserEmail(authentication.getName());
        model.addAttribute("resumes", resumes);
        return "users/layoutApplicant";
    }

    @PreAuthorize("hasAuthority('employee')")
    @GetMapping("/edit")
    public String employerInfo(Model model, Authentication auth) {
        UserDto user = service.getUserByEmail(auth.getName());
        model.addAttribute("accType", user.getAccType());
        return "edit/editUser";
    }

    @PreAuthorize("hasAuthority('employee')")
    @PostMapping("/edit")
    public String editProfile(EditUserDto userDto, Authentication auth) {
        service.editUser(userDto,auth);
        return "redirect:/profile";
    }

    @PreAuthorize("hasAuthority('employee')")
    @GetMapping("/uploadImage")
    public String uploadImageToProfile(Model model, Authentication authentication) {
        UserDto user = service.getUserByEmail(authentication.getName());
        model.addAttribute("accType", user.getAccType());
        return "edit/uploadImage";
    }

    @PreAuthorize("hasAuthority('employee')")
    @PostMapping("/uploadImage")
    public String uploadImageProfile(Model model, String email, MultipartFile file) {
        UserDto user = service.getUserByEmail(email);
        service.uploadImage(file, email);
        model.addAttribute("accType", user.getAccType());
        return "redirect:/profile";
    }

    @PreAuthorize("hasAuthority('employee')")
    @GetMapping("/changePassword")
    public String changePassword(Model model, Authentication authentication) {
        UserDto user = service.getUserByEmail(authentication.getName());
        model.addAttribute("accType", user.getAccType());
        return "edit/setNewPassword";
    }

    @PreAuthorize("hasAuthority('employee')")
    @PostMapping("/changePassword")
    public String SetNewPassword(Model model, String email, String oldPassword, String newPassword) {
        UserDto user = service.getUserByEmail(email);
        service.changePassword(oldPassword,newPassword,email);
        model.addAttribute("accType", user.getAccType());
        return "redirect:/profile";
    }

}
