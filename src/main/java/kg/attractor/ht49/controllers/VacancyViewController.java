package kg.attractor.ht49.controllers;

import jakarta.validation.Valid;
import kg.attractor.ht49.dto.CategoryDto;
import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.dto.vacancies.VacancyCreateDto;
import kg.attractor.ht49.dto.vacancies.VacancyDto;
import kg.attractor.ht49.dto.vacancies.VacancyEditDto;
import kg.attractor.ht49.AuthAdapter;
import kg.attractor.ht49.services.interfaces.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/vacancies")
@RequiredArgsConstructor
public class VacancyViewController {
    private final VacancyService service;
    private final UserService uService;
    private final CategoryService categoryService;
    private final AuthAdapter authAdapter;
    private final ResumeService resumeService;
    private final RespondedApplicantsService respondedApplicantsService;

    @GetMapping("/info/{vacancyId}")
    public String getVacancyById(@PathVariable Long vacancyId, Model model) {
        String email = authAdapter.getAuthUser().getEmail();
        VacancyDto vacancy = service.getVacancyById(vacancyId);
        model.addAttribute("vacancy", vacancy);
        List<VacancyDto> vacanciesOfAuthor = service.getActiveVacanciesByCompany(vacancy.getAuthorEmail());
        model.addAttribute("employers", vacanciesOfAuthor);
        UserDto user = uService.getUserByEmail(email);

        if (Objects.equals(user.getAccType(), "employee")) {
            List<ResumeDto> resumes = resumeService.getResumeByCategory(user.getEmail(), vacancy.getCategory());
            model.addAttribute("resumes", resumes);
            model.addAttribute("applicant", user);

            Long respId = respondedApplicantsService.ifThereResumeIdAndVacancyId(resumes, vacancyId);
            model.addAttribute("respId", respId);
        }
        return "vacancy/vacancyInfo";
    }

    @GetMapping("/filter")
    public String getVacancyByCategory(@RequestParam String category,@RequestParam(name = "page",defaultValue = "0")Integer page, Model model) {
        if(category.isBlank() || category.isEmpty()) return "redirect:/";
        Page<VacancyDto> vacancies = service.getVacanciesByCategory(category,page);
        model.addAttribute("vacancies", vacancies);
        List<CategoryDto> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("category",category);
        model.addAttribute("page",page);
        return "vacancy/filteredVacancies";
    }

    @GetMapping("/create")
    public String getVacancyCreatePage(Model model) {
        List<CategoryDto> categories = categoryService.getCategories();
        VacancyCreateDto vacancyCreateDto = new VacancyCreateDto();
        model.addAttribute("categories", categories);
        model.addAttribute("vacancyCreateDto", vacancyCreateDto);
        return "vacancy/vacancyCreate";
    }

    @PostMapping("/create")
    public String createVacancy(@Valid VacancyCreateDto vacancyCreateDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<CategoryDto> categories = categoryService.getCategories();
            model.addAttribute("vacancyCreateDto", vacancyCreateDto);
            model.addAttribute("categories", categories);
            return "vacancy/vacancyCreate";
        }
        String email = authAdapter.getAuthUser().getEmail();
        service.createVacancyAndReturnId(vacancyCreateDto, email);
        model.addAttribute("user", uService.getUserByEmail(email));
        return "redirect:/profile";
    }

    @GetMapping("/edit")
    public String getVacancyEditPage(Model model, @RequestParam Long id) {
        VacancyEditDto vacancyEditDto = new VacancyEditDto();
        List<CategoryDto> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        VacancyDto dto = service.getVacancyById(id);
        model.addAttribute("vacancy", dto);
        model.addAttribute("vacancyEditDto", vacancyEditDto);
        return "vacancy/editVacancy";
    }

    @PostMapping("/edit")
    public String updateVacancy(@RequestParam Long id,@Valid VacancyEditDto vacancyEditDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.getCategories());
            model.addAttribute("vacancyEditDto", vacancyEditDto);
            VacancyDto dto = service.getVacancyById(id);
            model.addAttribute("vacancy", dto);
            return "vacancy/editVacancy";
        }
        String email = authAdapter.getAuthUser().getEmail();
        vacancyEditDto.setId(id);
        service.editVacancy(vacancyEditDto);
        model.addAttribute("user", uService.getUserByEmail(email));
        return "redirect:/profile";
    }

    @PostMapping("/changeActivation")
    public String changeActivation(@RequestParam Long id) {
        service.changeVacancyState(id);
        return "redirect:/profile";
    }

    @PostMapping("/applyToVacancy/{id}")
    public String applyToVacancy(@RequestParam Long resumeId, @PathVariable Long id) {
        respondedApplicantsService.applyToVacancy(resumeId, id);
        return "redirect:/vacancies/info/" + id;
    }

    @PostMapping("/updateVacancy")
    public String update(@Valid @RequestParam Long id) {
        service.updateVacancy(id);
        return "redirect:/profile";
    }
}
