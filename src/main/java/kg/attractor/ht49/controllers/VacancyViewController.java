package kg.attractor.ht49.controllers;

import kg.attractor.ht49.dto.vacancies.VacancyDto;
import kg.attractor.ht49.services.interfaces.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/vacancies")
@RequiredArgsConstructor
public class VacancyViewController {
   private final VacancyService service;

   @GetMapping
    public String getVacancies(Model model){
      List<VacancyDto> vacancies = service.getActiveVacancies();
       model.addAttribute("vacancies",vacancies);
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
}
