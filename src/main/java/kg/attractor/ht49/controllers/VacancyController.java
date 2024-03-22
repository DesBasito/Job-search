package kg.attractor.ht49.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import kg.attractor.ht49.dto.vacancies.VacancyDto;
import kg.attractor.ht49.dto.vacancies.VacancyEditDto;
import kg.attractor.ht49.exceptions.CategoryNotFoundException;
import kg.attractor.ht49.exceptions.UserNotFoundException;
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

    @GetMapping("/active")
    public ResponseEntity<List<VacancyDto>> getActiveVacancies() {
        return ResponseEntity.ok(service.getActiveVacancies());
    }

    @GetMapping("company/{id}")
    public ResponseEntity<List<VacancyDto>> getVacanciesByCompanyId(@Valid @Pattern(regexp = "^\\d+$", message = "only numbers") @PathVariable(name = "id") Long id) {
        List<VacancyDto> vacancies = service.getAllVacanciesByCompany(id);
        return ResponseEntity.ok(vacancies);
    }

    @GetMapping("company/active/{id}")
    public ResponseEntity<List<VacancyDto>> getActiveVacanciesByCompanyId(@Valid @Pattern(regexp = "^\\d+$", message = "only numbers") @PathVariable(name = "id") Long id) {
        List<VacancyDto> vacancies = service.getActiveVacanciesByCompany(id);
        return ResponseEntity.ok(vacancies);
    }

    @GetMapping("/vacancy")
    public ResponseEntity<List<VacancyDto>> getVacanciesOfRespondedApplicantEmail(@Valid @Email @RequestParam(name = "email", defaultValue = "") String email) {
        return ResponseEntity.ok(service.getVacanciesByRespondedApplicant(email.strip()));
    }

    @GetMapping("/category")
    public ResponseEntity<List<VacancyDto>> getVacanciesByCategory(@Valid @RequestParam(name = "category", defaultValue = "") String category) {
        String categoryStriped = category.strip();
        return ResponseEntity.ok(service.getVacanciesByCategory(categoryStriped));
    }

    @PostMapping()
    public ResponseEntity<VacancyDto> createVacancy(@Valid @RequestParam VacancyDto vacancy) {
        Long id = service.createVacancyAndReturnId(vacancy);
        return ResponseEntity.ok(service.getVacancyById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVacancyById(@PathVariable(name = "id") Long id) {
        if (service.deleteVacancyById(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/edit")
    public ResponseEntity<VacancyDto> editVacancy(@RequestBody VacancyEditDto vacancy) {
        service.editVacancy(vacancy);
        return ResponseEntity.ok(service.getVacancyById(vacancy.getId()));
    }

    @PostMapping("/status/{id}")
    public ResponseEntity<?> changeVacancyState(@PathVariable(name = "id") Long id) {
        service.changeVacancyState(id);
        return ResponseEntity.ok(service.getVacancyById(id));
    }
}
