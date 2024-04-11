package kg.attractor.ht49.controllers;

import kg.attractor.ht49.dto.users.EditUserDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.dto.vacancies.VacancyDto;
import kg.attractor.ht49.services.interfaces.UserService;
import kg.attractor.ht49.services.interfaces.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("employer")
@RequiredArgsConstructor
public class EmployerController {
    private final UserService service;
    private final VacancyService vacancyService;

    @PreAuthorize("hasAuthority('employer')")
    @GetMapping("/edit")
    public String employerInfo(Model model, Authentication authentication) {
        UserDto user = service.getUserByEmail(authentication.getName());
        model.addAttribute("accType",user.getAccType());
        return "edit/editUser";
    }

    @PreAuthorize("hasAuthority('employer')")
    @PostMapping("/edit")
    public String editProfile(EditUserDto userDto, Authentication auth) {
        service.editUser(userDto,auth);
        return "redirect:/vacancies";
    }

    @PreAuthorize("hasRole('employer')")
    @GetMapping("/uploadImage")
    public String uploadImageToProfile(Model model, Authentication authentication) {
        UserDto user = service.getUserByEmail(authentication.getName());
        model.addAttribute("accType", user.getAccType());
        return "edit/uploadImage";
    }

    @PreAuthorize("hasAuthority('employer')")
    @PostMapping("/uploadImage")
    public String uploadImageProfile(Model model, Authentication authentication, MultipartFile file) {
        UserDto user = service.getUserByEmail(authentication.getName());
        service.uploadImage(file, authentication);
        model.addAttribute("accType", user.getAccType());
        return "redirect:/profile";
    }

    @PreAuthorize("hasAuthority('employer')")
    @GetMapping("/changePassword")
    public String changePassword(Model model, Authentication authentication) {
        UserDto user = service.getUserByEmail(authentication.getName());
        model.addAttribute("accType", user.getAccType());
        return "edit/setNewPassword";
    }

    @PreAuthorize("hasAuthority('employer')")
    @PostMapping("/changePassword")
    public String SetNewPassword(Model model, Authentication authentication, @RequestParam String oldPassword, @RequestParam String newPassword) {
        UserDto user = service.getUserByEmail(authentication.getName());
        service.changePassword(oldPassword,newPassword,authentication.getName());
        model.addAttribute("accType", user.getAccType());
        return "redirect:/profile";
    }
}
