package kg.attractor.ht49.controllers;

import kg.attractor.ht49.dto.ResumeDto;
import kg.attractor.ht49.exceptions.CategoryNotFoundException;
import kg.attractor.ht49.exceptions.ResumeNotFoundException;
import kg.attractor.ht49.exceptions.UserNotFoundException;
import kg.attractor.ht49.services.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService service;

    @GetMapping("resumes")
    public ResponseEntity<List<ResumeDto>> getResumes() {
        var resumes = service.getResumes();
        return ResponseEntity.ok(resumes);
    }

    @GetMapping("resumes/{category}")
    public ResponseEntity<?> getResumesByCategory(@PathVariable(name = "category") String category) {
        try {
            List<ResumeDto> resumes = service.getResumeByCategory(category.strip());
            return ResponseEntity.ok(resumes);
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("resumes/userEmail")
    public ResponseEntity<?> getResumesByUser(@RequestParam(name = "email", defaultValue = "") String email) {
        try {
            List<ResumeDto> resumes = service.getResumeByUserEmail(email.strip());
            return ResponseEntity.ok(resumes);
        } catch (UserNotFoundException | ResumeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
