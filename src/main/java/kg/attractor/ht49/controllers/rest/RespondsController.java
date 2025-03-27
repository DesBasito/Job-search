package kg.attractor.ht49.controllers.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import kg.attractor.ht49.dto.RespondedApplicantDto;
import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.services.interfaces.RespondedApplicantsService;
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
        List<RespondedApplicantDto> respondents = service.getAllRespondents();
        return ResponseEntity.ok(respondents);
    }

    @GetMapping("/{vacancyId}")
    public ResponseEntity<List<ResumeDto>> getRespondedApplicantsToVacancy(@Valid @PathVariable Long vacancyId) {
        List<ResumeDto> resumes = service.getRespondedApplicantsByVacancyId(vacancyId);
        return ResponseEntity.ok(resumes);
    }

    @PostMapping("/applyToVacancy/{id}")
    public ResponseEntity<?> applyToVacancy(@Valid @Pattern(regexp = "^\\d+$", message = "enter only digits") @RequestParam Long resumeId, @PathVariable Long id) {
        service.applyToVacancy(resumeId, id);
        return ResponseEntity.ok(service.getAllRespondents());
    }
}
