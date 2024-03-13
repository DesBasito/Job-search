package kg.attractor.ht49.controllers;

import kg.attractor.ht49.dto.VacancyDto;
import kg.attractor.ht49.exceptions.CategoryNotFoundException;
import kg.attractor.ht49.exceptions.UserNotFoundException;
import kg.attractor.ht49.exceptions.VacancyNotFoundException;
import kg.attractor.ht49.services.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("vacancies")
public class VacancyController {
    private final VacancyService service;

    @GetMapping()
    public ResponseEntity<List<VacancyDto>> getVacancies() {
        return ResponseEntity.ok(service.getAllVacancies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRespondedApplicantsToVacancy(@PathVariable(name = "id") Long id){
        try {
            return ResponseEntity.ok(service.getRespondedApplicants(id));
        }catch (VacancyNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/vacancy")
    public ResponseEntity<?> getVacanciesOfRespondedApplicantEmail(@RequestParam(name = "email",defaultValue = "") String email){
        try {
            return ResponseEntity.ok(service.getVacanciesOfRespondedApplicant(email.strip()));
        }catch (UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/category")
    public ResponseEntity<?> getVacanciesOfCategory(@RequestParam(name = "category",defaultValue = "") String category){
        try {
            String categoryStriped = category.strip();
            return ResponseEntity.ok(service.getVacanciesOfCategory(categoryStriped));
        }catch (CategoryNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
