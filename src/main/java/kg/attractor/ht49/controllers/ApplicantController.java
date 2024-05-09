package kg.attractor.ht49.controllers;

import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.dto.users.EditUserDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.services.AuthAdapter;
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
    private final AuthAdapter adapter;

    @PreAuthorize("hasAuthority('employee')")
    @GetMapping("/edit")
    public String employerInfo(Model model) {
        String email = adapter.getAuthUser().getEmail();
        UserDto user = service.getUserByEmail(email);
        model.addAttribute("accType", user.getAccType());
        return "edit/editUser";
    }

    @PreAuthorize("hasAuthority('employee')")
    @PostMapping("/edit")
    public String editProfile(EditUserDto userDto) {
        String email = adapter.getAuthUser().getEmail();
        service.editUser(userDto, email);
        return "redirect:/profile";
    }

    @PreAuthorize("hasAuthority('employee')")
    @GetMapping("/uploadImage")
    public String uploadImageToProfile(Model model) {
        String email = adapter.getAuthUser().getEmail();
        UserDto user = service.getUserByEmail(email);
        model.addAttribute("accType", user.getAccType());
        return "edit/uploadImage";
    }

    @PreAuthorize("hasAuthority('employee')")
    @PostMapping("/uploadImage")
    public String uploadImageProfile(Model model, MultipartFile file) {
        String email = adapter.getAuthUser().getEmail();
        UserDto user = service.getUserByEmail(email);
        service.uploadImage(file, email);
        model.addAttribute("accType", user.getAccType());
        return "redirect:/profile";
    }

    @PreAuthorize("hasAuthority('employee')")
    @GetMapping("/changePassword")
    public String changePassword(Model model) {
        String email = adapter.getAuthUser().getEmail();
        UserDto user = service.getUserByEmail(email);
        model.addAttribute("accType", user.getAccType());
        return "edit/setNewPassword";
    }

    @PreAuthorize("hasAuthority('employee')")
    @PostMapping("/changePassword")
    public String SetNewPassword(Model model, String oldPassword, String newPassword) {
        String email = adapter.getAuthUser().getEmail();
        UserDto user = service.getUserByEmail(email);
        service.changePassword(oldPassword,newPassword,email);
        model.addAttribute("accType", user.getAccType());
        return "redirect:/profile";
    }

}
