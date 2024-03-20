package kg.attractor.ht49.controllers;

import kg.attractor.ht49.dto.RespondedApplicantDto;
import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.exceptions.UserNotFoundException;
import kg.attractor.ht49.services.RespondedApplicantsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("responses")
public class RespondsController {
    private final RespondedApplicantsService service;

    @GetMapping()
    public ResponseEntity<List<RespondedApplicantDto>> getAllRespondedApplicants(){
        List<RespondedApplicantDto> dtos = service.getAllRespondents();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{vacancyId}")
    public ResponseEntity<?> getRespondedApplicantsToVacancy(@PathVariable Long vacancyId){
        List<ResumeDto> dtos = service.getRespondedApplicantsByVacancyId(vacancyId);
        if (dtos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vacancy by id: "+vacancyId+" not found");
        }
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/applyToVacancy")
    public ResponseEntity<?> applyToVacancy(@RequestParam String email, Long vacancyId){
        try {
            service.ApplyToVacancy(email,vacancyId);
            return ResponseEntity.ok(service.getAllRespondents());
        }catch (UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
