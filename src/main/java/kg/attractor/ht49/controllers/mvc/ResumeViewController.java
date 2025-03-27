package kg.attractor.ht49.controllers.mvc;

import jakarta.validation.Valid;
import kg.attractor.ht49.dto.CategoryDto;
import kg.attractor.ht49.dto.ContactInfo.ContactsInfoWithIdDto;
import kg.attractor.ht49.dto.educations.EducationInfoForFrontDto;
import kg.attractor.ht49.dto.resumes.EditResumeDto;
import kg.attractor.ht49.dto.resumes.ResumeCreateDto;
import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.dto.workExpInfo.WorkExpInfoForFrontDto;
import kg.attractor.ht49.models.Resume;
import kg.attractor.ht49.services.Components.AuthAdapter;
import kg.attractor.ht49.services.interfaces.CategoryService;
import kg.attractor.ht49.services.interfaces.ContactsInfoService;
import kg.attractor.ht49.services.interfaces.ResumeService;
import kg.attractor.ht49.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String getResumeById(@PathVariable Long id, Model model) {
        Resume resume = service.getResumeModel(id);
        UserDto user = userService.getUserByEmail(resume.getApplicant().getEmail());
        List<WorkExpInfoForFrontDto> workExpInfos = service.getWorkExpInfoByResumeId(id);
        List<EducationInfoForFrontDto> educationInfo = service.getEducationInfoByResumeId(id);
        List<ContactsInfoWithIdDto> contacts = contactsInfoService.getContactsByResumeId(resume);
        model.addAttribute("user", user);
        model.addAttribute("resume", resume);
        model.addAttribute("works", workExpInfos);
        model.addAttribute("educations", educationInfo);
        model.addAttribute("contacts", contacts);
        return "resume/resumesInfo";
    }


    @GetMapping("/create")
    public String getResumeCreatePage(Model model) {
        model.addAttribute("categories", categoryService.getCategories());
        ResumeCreateDto resumeCreateDto = new ResumeCreateDto();
        model.addAttribute("resumeCreateDto", resumeCreateDto);
        return "resume/resumeCreate";
    }

    @PostMapping("/create")
    public String createResume(@Valid ResumeCreateDto resumeCreateDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.getCategories());
            model.addAttribute("resumeCreateDto", resumeCreateDto);
            return "resume/resumeCreate";
        }
        String email = adapter.getAuthUser().getEmail();
        Long id = service.createResume(resumeCreateDto, email);
        return "redirect:/resume/" + id;
    }


    @GetMapping("/filter")
    public String getVacancyByCategory(@RequestParam String category, @RequestParam(name = "page",defaultValue = "0")Integer page, Model model) {
        if(category.isBlank() || category.isEmpty()) return "redirect:/";
        Page<ResumeDto> vacancies = service.getResumesByCategory(category.strip(),page);
        model.addAttribute("resumes", vacancies);
        List<CategoryDto> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("category",category);
        model.addAttribute("page",page);
        return "resume/filteredResumes";
    }
    @GetMapping("/edit/{id}")
    public String getResumeEditPage(Model model, @PathVariable Long id) {
        String email = adapter.getAuthUser().getEmail();
        List<CategoryDto> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        UserDto user = userService.getUserByEmail(email);
        model.addAttribute("user", user);
        EditResumeDto editResumeDto = service.getResumeForEdit(id);
        model.addAttribute("editResumeDto", editResumeDto);
        return "resume/editResume";
    }



    @PostMapping("/edit/{id}")
    public String updateResume(@PathVariable Long id, @Valid EditResumeDto editResumeDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            String email = adapter.getAuthUser().getEmail();
            List<CategoryDto> categories = categoryService.getCategories();
            model.addAttribute("categories", categories);
            UserDto user = userService.getUserByEmail(email);
            model.addAttribute("user", user);
            model.addAttribute("editResumeDto", editResumeDto);
            return "resume/editResume";
        }
        String email = adapter.getAuthUser().getEmail();
        editResumeDto.setId(id);
        service.editResume(editResumeDto, email);
        model.addAttribute("user", userService.getUserByEmail(email));
        return "redirect:/resume/" + editResumeDto.getId();
    }

    @PostMapping("/changeState")
    public String changeActivation(@RequestParam Long id) {
        service.changeResumeState(id);
        return "redirect:/profile";
    }

    @PostMapping("/updateResume")
    public String update(@Valid @RequestParam Long id) {
        service.updateResume(id);
        return "redirect:/profile";
    }
}
