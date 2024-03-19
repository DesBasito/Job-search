package kg.attractor.ht49.controllers;

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
    public ResponseEntity<?> getVacanciesByCompanyId(@PathVariable(name = "id") Long id) {
        List<VacancyDto> vacancies = service.getAllVacanciesByCompany(id);
        if (vacancies == null || vacancies.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No resumes were found :(");
        }
        return ResponseEntity.ok(vacancies);
    }

    @GetMapping("company/active/{id}")
    public ResponseEntity<?> getActiveVacanciesByCompanyId(@PathVariable(name = "id") Long id) {
        List<VacancyDto> vacancies = service.getActiveVacanciesByCompany(id);
        if (vacancies == null || vacancies.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No resumes were found :(");
        }
        return ResponseEntity.ok(vacancies);
    }

    @GetMapping("/vacancy")
    public ResponseEntity<?> getVacanciesOfRespondedApplicantEmail(@RequestParam(name = "email", defaultValue = "") String email) {
        try {
            return ResponseEntity.ok(service.getVacanciesByRespondedApplicant(email.strip()));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/category")
    public ResponseEntity<?> getVacanciesByCategory(@RequestParam(name = "category", defaultValue = "") String category) {
        try {
            String categoryStriped = category.strip();
            return ResponseEntity.ok(service.getVacanciesByCategory(categoryStriped));
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<Long> createVacancy(@RequestBody VacancyDto vacancy) {
        service.createVacancy(vacancy);
        Long id = service.createVacancyAndReturnId(vacancy);
        return ResponseEntity.ok(id);
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
        if (service.getVacancyById(id) != null){
            service.changeVacancyState(id);
            return ResponseEntity.ok(service.getVacancyById(id));
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Vacancy by id "+id+" not found");
    }
}
