package kg.attractor.ht49.controllers;

import jakarta.validation.Valid;
import kg.attractor.ht49.dto.CategoryDto;
import kg.attractor.ht49.dto.ContactInfo.ContactsInfoDto;
import kg.attractor.ht49.dto.ContactInfo.ContactsInfoWithIdDto;
import kg.attractor.ht49.dto.educations.EducationInfoForFrontDto;
import kg.attractor.ht49.dto.resumes.EditResumeDto;
import kg.attractor.ht49.dto.resumes.ResumeCreateDto;
import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.dto.workExpInfo.WorkExpInfoForFrontDto;
import kg.attractor.ht49.services.interfaces.CategoryService;
import kg.attractor.ht49.services.interfaces.ContactsInfoService;
import kg.attractor.ht49.services.interfaces.ResumeService;
import kg.attractor.ht49.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    private final ContactsInfoService contactsInfoService;

    @GetMapping("/{id}")
    public String getResumeById(@PathVariable Long id, Model model){
        ResumeDto resume = service.getResumeById(id);
        model.addAttribute("resume",resume);
        UserDto user = userService.getUserByEmail(resume.getUserEmail());
        model.addAttribute("user",user);
        List<WorkExpInfoForFrontDto> workExpInfos = service.getWorkExpInfoByResumeId(id);
        model.addAttribute("works",workExpInfos);
        List<EducationInfoForFrontDto> educationInfo = service.getEducationInfoByResumeId(id);
        model.addAttribute("educations",educationInfo);
        List<ContactsInfoWithIdDto> contacts = contactsInfoService.getContactsByResumeId(id);
        model.addAttribute("contacts",contacts);
        return "resume/resumesInfo";
    }


    @GetMapping("/create")
    public String getResumeCreatePage(Model model){
        model.addAttribute("categories",categoryService.getCategories());
        return "resume/resumeCreate";
    }

    @PostMapping("/create")
    public String createResume(ResumeCreateDto createDto, Authentication authentication){
        Long id = service.createResume(createDto,authentication);
        return "redirect:/resume/"+id;
    }


    @GetMapping()
    public String getResumes(Model model, @RequestParam(name = "page", defaultValue = "0") Integer page){
        if (page < 0){
            page = 0;
        }
        model.addAttribute("page", page);
        Page<ResumeDto> resumes = service.getResumesPage(page);
        model.addAttribute("resumes",resumes);
        List<CategoryDto> categories = categoryService.getCategories();
        model.addAttribute("categories",categories);
        return "resume/resumeList";
    }

    @GetMapping("/filter")
    public String getVacancyByCategory(@RequestParam String category, Model model, Authentication authentication){
        List<ResumeDto> resumes = service.getResumeByCategory(category);
        model.addAttribute("resumes", resumes);
        List<CategoryDto> categories = categoryService.getCategories();
        model.addAttribute("categories",categories);
        return "resume/filteredResumes";
    }

    @GetMapping("/update")
    public String getResumeEditPage(Model model, @RequestParam Long id){
        List<CategoryDto> categories = categoryService.getCategories();
        model.addAttribute("categories",categories);
        ResumeDto resume = service.getResumeById(id);
        model.addAttribute("resume",resume);
        return "resume/editResume";
    }

    @PostMapping("/update")
    public String updateResume(@RequestParam Long id, Model model, @Valid EditResumeDto editDto, Authentication authentication){
        editDto.setId(id);
        service.editResume(editDto,authentication);
        model.addAttribute("user",userService.getUserByEmail(authentication.getName()));
        return "redirect:/profile";
    }
}
