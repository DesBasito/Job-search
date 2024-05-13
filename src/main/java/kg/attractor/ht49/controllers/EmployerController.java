package kg.attractor.ht49.controllers;

import kg.attractor.ht49.dto.users.EditUserDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.AuthAdapter;
import kg.attractor.ht49.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("employer")
@RequiredArgsConstructor
public class EmployerController {
    private final UserService service;
    private final AuthAdapter adapter;

    @GetMapping("/edit")
    public String employerInfo(Model model) {
        String email = adapter.getAuthUser().getEmail();
        UserDto user = service.getUserByEmail(email);
        model.addAttribute("accType",user.getAccType());
        return "edit/editUser";
    }

    @PostMapping("/edit")
    public String editProfile(EditUserDto userDto) {
        String email = adapter.getAuthUser().getEmail();
        service.editUser(userDto,email);
        return "redirect:/vacancies";
    }

    @GetMapping("/uploadImage")
    public String uploadImageToProfile(Model model) {
        String email = adapter.getAuthUser().getEmail();
        UserDto user = service.getUserByEmail(email);
        model.addAttribute("accType", user.getAccType());
        return "edit/uploadImage";
    }

    @PostMapping("/uploadImage")
    public String uploadImageProfile(Model model, MultipartFile file) {
        String email = adapter.getAuthUser().getEmail();
        UserDto user = service.getUserByEmail(email);
        service.uploadImage(file, email);
        model.addAttribute("accType", user.getAccType());
        return "redirect:/profile";
    }

    @GetMapping("/changePassword")
    public String changePassword(Model model) {
        String email = adapter.getAuthUser().getEmail();
        UserDto user = service.getUserByEmail(email);
        model.addAttribute("accType", user.getAccType());
        return "edit/setNewPassword";
    }

    @PostMapping("/changePassword")
    public String SetNewPassword(Model model,@RequestParam String oldPassword, @RequestParam String newPassword) {
        String email = adapter.getAuthUser().getEmail();
        UserDto user = service.getUserByEmail(email);
        service.changePassword(oldPassword,newPassword,email);
        model.addAttribute("accType", user.getAccType());
        return "redirect:/profile";
    }
}
