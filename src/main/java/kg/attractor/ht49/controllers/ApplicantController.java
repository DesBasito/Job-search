package kg.attractor.ht49.controllers;

import jakarta.validation.constraints.Email;
import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.dto.users.EditUserDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.services.interfaces.ResumeService;
import kg.attractor.ht49.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
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

//    @GetMapping("profile")
//    public String applicantInfo(Model model, Authentication authentication) {
//        UserDto user = service.getUserByEmail(authentication.getName());
//        model.addAttribute("user", user);
//        List<ResumeDto> resumes = resumeService.getResumesByUserEmail(authentication.getName());
//        model.addAttribute("resumes", resumes);
//        return "users/layoutApplicant";
//    }

//    @GetMapping("/edit")
//    public String employerInfo(Model model, Authentication auth) {
//        UserDto user = service.getUserByEmail(auth.getName());
//        model.addAttribute("accType", user.getAccType());
//        return "users/editUser";
//    }
//    @PostMapping("/edit")
//    public String editProfile(EditUserDto userDto, Authentication auth) {
//        service.editUser(userDto,auth);
//        return "redirect:/vacancies";
//    }

    @GetMapping("profile/{email}")
    public String applicantInfo(Model model, @PathVariable String email) {
        UserDto user = service.getUserByEmail(email);
        model.addAttribute("user", user);
        List<ResumeDto> resumes = resumeService.getResumesByUserEmail(email);
        model.addAttribute("resumes", resumes);
        return "users/layoutApplicant";
    }
    @GetMapping("/edit/{email}")
    public String editProfile(Model model, @PathVariable @Email String email) {
        UserDto user = service.getUserByEmail(email);
        model.addAttribute("accType", user.getAccType());
        return "edit/editUser";
    }
    @PostMapping("/edit")
    public String editProfile(EditUserDto userDto, String email) {
        service.editUserTest(userDto,email);
        return "redirect:/vacancies";
    }


    @GetMapping("/uploadImage/{email}")
    public String uploadImageToProfile(Model model, @PathVariable String email) {
        UserDto user = service.getUserByEmail(email);
        model.addAttribute("accType", user.getAccType());
        return "edit/uploadImage";
    }

    @PostMapping("/uploadImage")
    public String uploadImageProfile(Model model, String email, MultipartFile file) {
        UserDto user = service.getUserByEmail(email);
        service.uploadImage(file, email);
        model.addAttribute("accType", user.getAccType());
        return "redirect:/vacancies";
    }


    @GetMapping("/changePassword/{email}")
    public String changePassword(Model model, @PathVariable String email) {
        UserDto user = service.getUserByEmail(email);
        model.addAttribute("accType", user.getAccType());
        return "edit/setNewPassword";
    }

    @PostMapping("/changePassword")
    public String SetNewPassword(Model model, String email, String oldPassword, String newPassword) {
        UserDto user = service.getUserByEmail(email);
        service.changePassword(oldPassword,newPassword,email);
        model.addAttribute("accType", user.getAccType());
        return "redirect:/vacancies";
    }




}
