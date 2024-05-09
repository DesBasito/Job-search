package kg.attractor.ht49.controllers.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import kg.attractor.ht49.dto.CategoryDto;
import kg.attractor.ht49.dto.vacancies.VacancyCreateDto;
import kg.attractor.ht49.dto.vacancies.VacancyDto;
import kg.attractor.ht49.dto.vacancies.VacancyEditDto;
import kg.attractor.ht49.services.AuthAdapter;
import kg.attractor.ht49.services.interfaces.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("restVacancies")
@RequiredArgsConstructor
@RequestMapping("api/vacancies")
public class VacancyController {
    private final VacancyService service;
    private final AuthAdapter adapter;

    @GetMapping()
    public ResponseEntity<List<VacancyDto>> getVacancies() {
        return ResponseEntity.ok(service.getAllVacancies());
    }


    @GetMapping("company/{email}")
    public ResponseEntity<List<VacancyDto>> getVacanciesByCompanyId(@PathVariable String email) {
        List<VacancyDto> vacancies = service.getAllVacanciesByCompany(email);
        return ResponseEntity.ok(vacancies);
    }

    @GetMapping("company/active/{email}")
    public ResponseEntity<List<VacancyDto>> getActiveVacanciesByCompanyId(@Email @PathVariable(name = "email") String email) {
        List<VacancyDto> vacancies = service.getActiveVacanciesByCompany(email);
        return ResponseEntity.ok(vacancies);
    }

    @GetMapping("/vacancy")
    public ResponseEntity<List<VacancyDto>> getVacanciesOfRespondedApplicantEmail(@Valid @Email @RequestParam(name = "email", defaultValue = "") String email) {
        return ResponseEntity.ok(service.getVacanciesByRespondedApplicant(email.strip()));
    }

    @GetMapping("/paging")
    public ResponseEntity<Page<VacancyDto>> getVacancies(@RequestParam(name = "page", defaultValue = "0") Integer page, @RequestParam(name = "filter",defaultValue = "null") String filter){
        page = page > 0 ? page - 1 : 0;
        Page<VacancyDto> vacancies = service.getActiveVacanciesPage(page, filter);
        return ResponseEntity.ok(vacancies);
    }

    @GetMapping("/{email}/paging")
    public ResponseEntity<Page<VacancyDto>> getVacanciesByProfile(@RequestParam(name = "page", defaultValue = "0") Integer page, @PathVariable String email){
        page = page > 0 ? page - 1 : 0;
        Page<VacancyDto> vacancies = service.getActiveVacanciesPageByEmail(page,email);
        return ResponseEntity.ok(vacancies);
    }

    @GetMapping("/category")
    public ResponseEntity<List<VacancyDto>> getVacanciesByCategory(@Valid @RequestParam(name = "category", defaultValue = "") String category) {
        String categoryStriped = category.strip();
        return ResponseEntity.ok(service.getVacanciesByCategory(categoryStriped));
    }

    @PostMapping()
    public ResponseEntity<VacancyDto> createVacancy(@Valid VacancyCreateDto vacancy) {
        String email = adapter.getAuthUser().getEmail();
        Long id = service.createVacancyAndReturnId(vacancy,email);
        return ResponseEntity.ok(service.getVacancyById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVacancyById(@PathVariable(name = "id") Long id) {
        if (service.deleteVacancyById(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping()
    public ResponseEntity<VacancyDto> editVacancy(@RequestBody VacancyEditDto vacancy,Authentication auth) {
        service.editVacancy(vacancy,auth);
        return ResponseEntity.ok(service.getVacancyById(vacancy.getId()));
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<?> changeVacancyState(@PathVariable(name = "id") Long id) {
        service.changeVacancyState(id);
        return ResponseEntity.ok(service.getVacancyById(id));
    }
}
