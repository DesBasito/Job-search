package kg.attractor.ht49.controllers;
import kg.attractor.ht49.dto.VacancyDto;
import kg.attractor.ht49.services.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
