package kg.attractor.ht49.controllers;

import jakarta.validation.Valid;
import kg.attractor.ht49.dto.CategoryDto;
import kg.attractor.ht49.dto.ContactInfo.ContactsInfoDto;
import kg.attractor.ht49.dto.ContactInfo.ContactsInfoWithIdDto;
import kg.attractor.ht49.dto.educations.EducationInfoEditDto;
import kg.attractor.ht49.dto.educations.EducationInfoForFrontDto;
import kg.attractor.ht49.dto.resumes.EditResumeDto;
import kg.attractor.ht49.dto.resumes.ResumeCreateDto;
import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.dto.vacancies.VacancyDto;
import kg.attractor.ht49.dto.workExpInfo.WorkExpInfoEditDto;
import kg.attractor.ht49.dto.workExpInfo.WorkExpInfoForFrontDto;
import kg.attractor.ht49.models.Resume;
import kg.attractor.ht49.services.AuthAdapter;
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
    private final AuthAdapter adapter;
    private final ContactsInfoService contactsInfoService;

    @GetMapping("/{id}")
    public String getResumeById(@PathVariable Long id, Model model){
        Resume resume = service.getResumeModel(id);
        UserDto user = userService.getUserByEmail(resume.getApplicant().getEmail());
        List<WorkExpInfoForFrontDto> workExpInfos = service.getWorkExpInfoByResumeId(id);
        List<EducationInfoForFrontDto> educationInfo = service.getEducationInfoByResumeId(id);
        List<ContactsInfoWithIdDto> contacts = contactsInfoService.getContactsByResumeId(resume);
        model.addAttribute("user",user);
        model.addAttribute("resume",resume);
        model.addAttribute("works",workExpInfos);
        model.addAttribute("educations",educationInfo);
        model.addAttribute("contacts",contacts);
        return "resume/resumesInfo";
    }


    @GetMapping("/create")
    public String getResumeCreatePage(Model model){
        model.addAttribute("categories",categoryService.getCategories());
        return "resume/resumeCreate";
    }

    @PostMapping("/create")
    public String createResume(ResumeCreateDto createDto){
        String email = adapter.getAuthUser().getEmail();
        Long id = service.createResume(createDto,email);
        return "redirect:/resume/"+id;
    }


    @GetMapping("/filter")
    public String getVacancyByCategory(@RequestParam String category, Model model){
        List<ResumeDto> resumes = service.getResumeByCategory(category);
        model.addAttribute("resumes", resumes);
        List<CategoryDto> categories = categoryService.getCategories();
        model.addAttribute("categories",categories);
        return "resume/filteredResumes";
    }

    @GetMapping("/edit/{id}")
    public String getResumeEditPage(Model model, @PathVariable Long id){
        String email = adapter.getAuthUser().getEmail();
        List<CategoryDto> categories = categoryService.getCategories();
        model.addAttribute("categories",categories);
        UserDto user = userService.getUserByEmail(email);
        model.addAttribute("user",user);
        EditResumeDto resume = service.getResumeForEdit(id);
        model.addAttribute("resume",resume);
        return "resume/editResume";
    }


    @PostMapping("/edit/{id}")
    public String updateResume(Model model,@PathVariable Long id ,@Valid EditResumeDto editDto){
        String email = adapter.getAuthUser().getEmail();
        editDto.setId(id);
        service.editResume(editDto,email);
        model.addAttribute("user",userService.getUserByEmail(email));
        return "redirect:/resume/"+editDto.getId();
    }

    @PostMapping("/changeState")
    public String changeActivation(@RequestParam Long id){
        service.changeResumeState(id);
        return "redirect:/profile";
    }

    @PostMapping("/updateResume")
    public String update(@Valid @RequestParam Long id) {
        service.updateResume(id);
        return "redirect:/profile";
    }
}
