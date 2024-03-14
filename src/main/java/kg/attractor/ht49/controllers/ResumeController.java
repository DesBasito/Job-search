package kg.attractor.ht49.controllers;

import kg.attractor.ht49.dto.ResumeDto;
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

    @GetMapping("/{category}")
    public ResponseEntity<?> getResumesByCategory(@PathVariable(name = "category") String category) {
        try {
            List<ResumeDto> resumes = service.getResumeByCategory(category.strip());
            return resumes == null ?
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resumes by category: " + category + " not found")
                    : ResponseEntity.ok(resumes);
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category: " + category + " not found");
        }
    }

    @GetMapping("/userEmail")
    public ResponseEntity<?> getResumesByUser(@RequestParam(name = "email", defaultValue = "") String email) {
        try {
            List<ResumeDto> resumes = service.getResumeByUserEmail(email.strip());
            if (resumes.isEmpty()) {
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resumes by user with email: " + email + " not found");
            }
            return ResponseEntity.ok(resumes);
        }catch (UserNotFoundException e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with email: "+email+" not found");
        }
    }
}
