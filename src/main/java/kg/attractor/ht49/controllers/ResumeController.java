package kg.attractor.ht49.controllers;

import jakarta.validation.Valid;
import kg.attractor.ht49.dto.resumes.ResumeCreateDto;
import kg.attractor.ht49.dto.resumes.EditResumeDto;
import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.exceptions.CategoryNotFoundException;
import kg.attractor.ht49.exceptions.UserNotFoundException;
import kg.attractor.ht49.services.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("resumes")
public class ResumeController {
    private final ResumeService service;

    @GetMapping()
    public ResponseEntity<List<ResumeDto>> getResumes() {
        var resumes = service.getResumes();
        return ResponseEntity.ok(resumes);
    }

    @GetMapping("/searchByName/{resumeName}")
    public ResponseEntity<?> getResumeByName(@Valid  @PathVariable(name = "resumeName") String rName) {
        List<ResumeDto> dto = service.getResumeByName(rName);
        return dto == null ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resume by name: " + rName + " not found")
                : ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResumeDto> getResumeById(@Valid @PathVariable(name = "id") Long id) {
        ResumeDto dto = service.getResumeById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ResumeDto>> getResumesByCategory(@Valid @PathVariable(name = "category") String category) {
            List<ResumeDto> resumes = service.getResumeByCategory(category.strip());
            return ResponseEntity.ok(resumes);
    }

    @GetMapping("/userEmail/{email}")
    public ResponseEntity<List<ResumeDto>> getResumesByUser(@Valid @PathVariable(name = "email") String email) {
            List<ResumeDto> resumes = service.getResumeByUserEmail(email.strip());
            return ResponseEntity.ok(resumes);
    }

    @PostMapping()
    public ResponseEntity<Long> createResume(@Valid @RequestBody ResumeCreateDto resume) {
        service.createResume(resume);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResumeById(@Valid @PathVariable(name = "id") Long id) {
        if (service.deleteResumeById(id)) {
            return ResponseEntity.ok(service.getResumes());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resumes by id: "+id+" not found");
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editResume(@Valid @RequestBody EditResumeDto resume) {
        if (service.getResumeById(resume.getId()) != null) {
            service.editResume(resume);
            return ResponseEntity.ok(service.getResumeById(resume.getId()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resume: "+resume+" does not exists");
    }

    @PostMapping("/status/{id}")
    public ResponseEntity<?> changeResumeState(@Valid @PathVariable(name = "id") Long id) {
        if (service.getResumeById(id) != null) {
            service.changeResumeState(id);
            return ResponseEntity.ok(service.getResumeById(id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resume by id " + id + " not found");
    }
}
