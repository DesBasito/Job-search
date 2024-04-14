package kg.attractor.ht49.controllers;

import jakarta.validation.Valid;
import kg.attractor.ht49.dto.CategoryDto;
import kg.attractor.ht49.dto.vacancies.VacancyCreateDto;
import kg.attractor.ht49.dto.vacancies.VacancyDto;
import kg.attractor.ht49.dto.vacancies.VacancyEditDto;
import kg.attractor.ht49.services.interfaces.CategoryService;
import kg.attractor.ht49.services.interfaces.UserService;
import kg.attractor.ht49.services.interfaces.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vacancies")
@RequiredArgsConstructor
public class VacancyViewController {
   private final VacancyService service;
   private final UserService uService;
   private final CategoryService categoryService;

   @GetMapping()
    public String getVacancies(Model model, @RequestParam(name = "page", defaultValue = "0") Integer page){
       if (page < 0){
           page = 0;
       }
       model.addAttribute("page", page);
       Page<VacancyDto> vacancies = service.getActiveVacanciesPage(page);
       model.addAttribute("vacancies",vacancies);
       List<CategoryDto> categories = categoryService.getCategories();
       model.addAttribute("categories",categories);
       return "main/main";
   }

   @GetMapping("/info/{vacancyId}")
    public String getVacancyById(@PathVariable Long vacancyId, Model model){
       VacancyDto vacancy = service.getVacancyById(vacancyId);
       model.addAttribute("vacancy", vacancy);
       List<VacancyDto> vacanciesOfAuthor = service.getActiveVacanciesByCompany(vacancy.getAuthorEmail());
       model.addAttribute("employers", vacanciesOfAuthor);
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

    @PreAuthorize("(hasAuthority('employer'))")
    @GetMapping("/create")
    public String getVacancyCreatePage(Model model){
        List<CategoryDto> categories = categoryService.getCategories();
        model.addAttribute("categories",categories);
        return "vacancy/vacancyCreate";
    }

    @PreAuthorize("(hasAuthority('employer'))")
    @PostMapping("/create")
    public String createVacancy(Model model, @Valid VacancyCreateDto createDto, Authentication authentication){
        service.createVacancyAndReturnId(createDto,authentication);
        model.addAttribute("user",uService.getUserByEmail(authentication.getName()));
        return "redirect:/profile";
    }

    @PreAuthorize("(hasAuthority('employer'))")
    @GetMapping("/update")
    public String getVacancyEditPage(Model model, @RequestParam Long id){
        List<CategoryDto> categories = categoryService.getCategories();
        model.addAttribute("categories",categories);
        VacancyDto dto = service.getVacancyById(id);
        model.addAttribute("vacancy",dto);
        return "vacancy/editVacancy";
    }

    @PreAuthorize("(hasAuthority('employer'))")
    @PostMapping("/update")
    public String updateVacancy(@RequestParam Long id,Model model, @Valid VacancyEditDto editDto, Authentication authentication){
       editDto.setId(id);
       service.editVacancy(editDto,authentication);
        model.addAttribute("user",uService.getUserByEmail(authentication.getName()));
        return "redirect:/profile";
    }

    @PostMapping("/changeActivation")
    public String changeActivation(@RequestParam Long id) {
        service.changeVacancyState(id);
        return "redirect:/profile";
    }
}
