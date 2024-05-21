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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String getVacancyById(@PathVariable Long vacancyId, Model model){
       String email = authAdapter.getAuthUser().getEmail();
       VacancyDto vacancy = service.getVacancyById(vacancyId);
       model.addAttribute("vacancy", vacancy);
       List<VacancyDto> vacanciesOfAuthor = service.getActiveVacanciesByCompany(vacancy.getAuthorEmail());
       model.addAttribute("employers", vacanciesOfAuthor);
       UserDto user = uService.getUserByEmail(email);

       if (Objects.equals(user.getAccType(),"employee")){
           List<ResumeDto> resumes = resumeService.getResumeByCategory(user.getEmail(),vacancy.getCategory());
           model.addAttribute("resumes",resumes);
           model.addAttribute("applicant",user);

           Long respId = respondedApplicantsService.ifThereResumeIdAndVacancyId(resumes,vacancyId);
           model.addAttribute("respId",respId);
       }
       return "vacancy/vacancyInfo";
   }

   @GetMapping("/filter")
    public String getVacancyByCategory(@RequestParam String category, Model model){
        List<VacancyDto> vacancies = service.getVacanciesByCategory(category);
        model.addAttribute("vacancies", vacancies);
       List<CategoryDto> categories = categoryService.getCategories();
       model.addAttribute("categories",categories);
        return "vacancy/filteredVacancies";
    }

    @GetMapping("/create")
    public String getVacancyCreatePage(Model model){
        List<CategoryDto> categories = categoryService.getCategories();
        model.addAttribute("categories",categories);
        return "vacancy/vacancyCreate";
    }

    @PostMapping("/create")
    public String createVacancy(Model model, @Valid VacancyCreateDto createDto){
       String email = authAdapter.getAuthUser().getEmail();
        service.createVacancyAndReturnId(createDto,email);
        model.addAttribute("user",uService.getUserByEmail(email));
        return "redirect:/profile";
    }

    @GetMapping("/edit")
    public String getVacancyEditPage(Model model, @RequestParam Long id){
        List<CategoryDto> categories = categoryService.getCategories();
        model.addAttribute("categories",categories);
        VacancyDto dto = service.getVacancyById(id);
        model.addAttribute("vacancy",dto);
        return "vacancy/editVacancy";
    }

    @PostMapping("/edit")
    public String updateVacancy(@RequestParam Long id,Model model, @Valid VacancyEditDto editDto){
       String email = authAdapter.getAuthUser().getEmail();
       editDto.setId(id);
       service.editVacancy(editDto);
        model.addAttribute("user",uService.getUserByEmail(email));
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
        return "redirect:/vacancies/info/"+id;
    }

    @PostMapping("/updateVacancy")
    public String update(@Valid @RequestParam Long id) {
        service.updateVacancy(id);
        return "redirect:/profile";
    }
}
