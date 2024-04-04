package kg.attractor.ht49.controllers;

import kg.attractor.ht49.dto.vacancies.VacancyDto;
import kg.attractor.ht49.services.interfaces.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
}
