package kg.attractor.ht49.controllers.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import kg.attractor.ht49.dto.RespondedApplicantDto;
import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.services.interfaces.RespondedApplicantsService;
import kg.attractor.ht49.services.interfaces.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("restResponses")
@RequiredArgsConstructor
@RequestMapping("api/responses")
public class RespondsController {
    private final RespondedApplicantsService service;

    @GetMapping()
    public ResponseEntity<List<RespondedApplicantDto>> getAllRespondedApplicants() {
        List<RespondedApplicantDto> dtos = service.getAllRespondents();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{vacancyId}")
    public ResponseEntity<List<ResumeDto>> getRespondedApplicantsToVacancy(@Valid @PathVariable Long vacancyId) {
        List<ResumeDto> dtos = service.getRespondedApplicantsByVacancyId(vacancyId);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/applyToVacancy/{id}")
    public ResponseEntity<?> applyToVacancy(@Valid @Pattern(regexp = "^\\d+$", message = "enter only digits") @RequestParam Long resumeId, @PathVariable Long id) {
        service.applyToVacancy(resumeId, id);
        return ResponseEntity.ok(service.getAllRespondents());
    }
}
