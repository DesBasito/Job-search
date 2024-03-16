package kg.attractor.ht49.controllers;

import kg.attractor.ht49.dto.VacancyDto;
import kg.attractor.ht49.exceptions.CategoryNotFoundException;
import kg.attractor.ht49.exceptions.UserNotFoundException;
import kg.attractor.ht49.exceptions.VacancyNotFoundException;
import kg.attractor.ht49.models.Vacancy;
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
        List<VacancyDto> vacancies = service.getAllVacanciesOfCompany(id);
        if (vacancies == null || vacancies.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No resumes were found :(");
        }
        return ResponseEntity.ok(vacancies);
    }

    @GetMapping("company/active/{id}")
    public ResponseEntity<?> getActiveVacanciesByCompanyId(@PathVariable(name = "id") Long id) {
        List<VacancyDto> vacancies = service.getActiveVacanciesOfCompany(id);
        if (vacancies == null || vacancies.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No resumes were found :(");
        }
        return ResponseEntity.ok(vacancies);
    }

    @GetMapping("/vacancy")
    public ResponseEntity<?> getVacanciesOfRespondedApplicantEmail(@RequestParam(name = "email", defaultValue = "") String email) {
        try {
            return ResponseEntity.ok(service.getVacanciesOfRespondedApplicant(email.strip()));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/category")
    public ResponseEntity<?> getVacanciesOfCategory(@RequestParam(name = "category", defaultValue = "") String category) {
        try {
            String categoryStriped = category.strip();
            return ResponseEntity.ok(service.getVacanciesOfCategory(categoryStriped));
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping()
    public HttpStatus createResume(@RequestBody Vacancy vacancy) {
        service.createVacancy(vacancy);
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVacancyById(@PathVariable(name = "id") Long id) {
        if (service.deleteVacancyById(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/edit")
    public HttpStatus editVacancy(@RequestBody VacancyDto vacancy) {
        service.editVacancy(vacancy);
        return HttpStatus.OK;
    }
}
