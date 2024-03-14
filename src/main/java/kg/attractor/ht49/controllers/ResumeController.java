package kg.attractor.ht49.controllers;

import kg.attractor.ht49.dto.ResumeDto;
import kg.attractor.ht49.exceptions.CategoryNotFoundException;
import kg.attractor.ht49.exceptions.UserNotFoundException;
import kg.attractor.ht49.models.Resume;
import kg.attractor.ht49.models.User;
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getResumeById(@PathVariable(name = "id")Long id){
        ResumeDto dto = service.getResumeById(id);
        return dto == null ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resume by Id: "+id+" not found")
                :ResponseEntity.ok(dto);
    }

    @GetMapping("/category/{category}")
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

    @GetMapping("/userEmail/{email}")
    public ResponseEntity<?> getResumesByUser(@PathVariable(name = "email") String email) {
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

    @PostMapping()
    public HttpStatus createResume(@RequestBody ResumeDto resume) {
        service.createResume(resume);
        return HttpStatus.OK;
    }

    @PostMapping("/delete/{id}")
    public HttpStatus deleteResumeById(@PathVariable(name = "id") Long id){
        service.deleteResumeById(id);
        return HttpStatus.OK;
    }
}
