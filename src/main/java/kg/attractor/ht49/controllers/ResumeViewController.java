package kg.attractor.ht49.controllers;

import kg.attractor.ht49.dto.educations.EducationInfoForFrontDto;
import kg.attractor.ht49.dto.resumes.ResumeCreateDto;
import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.dto.workExpInfo.WorkExpInfoForFrontDto;
import kg.attractor.ht49.services.interfaces.CategoryService;
import kg.attractor.ht49.services.interfaces.ResumeService;
import kg.attractor.ht49.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("resume")
public class ResumeViewController {
    private final ResumeService service;
    private final UserService userService;
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public String getResumeById(@PathVariable Long id, Model model){
        ResumeDto resume = service.getResumeById(id);
        model.addAttribute("resume",resume);
        UserDto user = service.getUserByResumesAuthorEmail(resume.getUserEmail());
        model.addAttribute("user",user);
        List<WorkExpInfoForFrontDto> workExpInfos = service.getWorkExpInfoByResumeId(id);
        model.addAttribute("works",workExpInfos);
        List<EducationInfoForFrontDto> educationInfo = service.getEducationInfoByResumeId(id);
        model.addAttribute("educations",educationInfo);
        return "resume/resumesInfo";
    }

    @PreAuthorize("(hasAuthority('employee'))")
    @GetMapping("/create")
    public String getResumeCreatePage(Model model){
        model.addAttribute("categories",categoryService.getCategories());
        return "resume/resumeCreate";
    }

    @PreAuthorize("(hasAuthority('employee'))")
    @PostMapping("/create")
    public String createResume(Model model, @RequestBody ResumeCreateDto createDto, Authentication authentication){
        service.createResume(createDto,authentication);
        model.addAttribute("user",userService.getUserByEmail(authentication.getName()));
        return "redirect:/applicant/profile";
    }
}
