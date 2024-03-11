package kg.attractor.ht49.controllers;

import kg.attractor.ht49.dto.VacancyDto;
import kg.attractor.ht49.exceptions.UserNotFoundException;
import kg.attractor.ht49.exceptions.VacancyNotFoundException;
import kg.attractor.ht49.services.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService service;

    @GetMapping("vacancies")
    public ResponseEntity<List<VacancyDto>> getVacancies() {
        return ResponseEntity.ok(service.getAllVacancies());
    }

    @GetMapping("vacancies/{name}")
    public ResponseEntity<?> getRespondedApplicantsToVacancy(@PathVariable String name){
        try {
            return ResponseEntity.ok(service.getUsers(name.strip()));
        }catch (VacancyNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("vacancies/vacancy")
    public ResponseEntity<?> getVacanciesOfRespondedApplicants(@RequestParam(name = "name",defaultValue = "Jane Smith") String name){
        try {
            return ResponseEntity.ok(service.getVacanciesOfRespondedApplicant(name.strip()));
        }catch (UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
