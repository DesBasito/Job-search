package kg.attractor.ht49.controllers;

import kg.attractor.ht49.dto.resumes.CreateResumeDto;
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
    public ResponseEntity<?> getResumeByName(@PathVariable(name = "resumeName") String rName) {
        List<ResumeDto> dto = service.getResumeByName(rName);
        return dto == null ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resume by name: " + rName + " not found")
                : ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getResumeById(@PathVariable(name = "id") Long id) {
        ResumeDto dto = service.getResumeById(id);
        return dto == null ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resume by Id: " + id + " not found")
                : ResponseEntity.ok(dto);
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
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with email: " + email + " not found");
        }
    }

    @PostMapping()
    public ResponseEntity<Long> createResume(@RequestBody ResumeCreateDto resume) {
        service.createResume(resume);
        Long id = service.createAndReturnIdResume(resume);
        return ResponseEntity.ok(id);
    }

    @PostMapping("/test/createResume")
    public ResponseEntity<Long> createResumeTesting(@RequestBody CreateResumeDto resume) {
        service.createResumeTest(resume);
//        Long id = service.createAndReturnIdResumeTest(resume);
        return ResponseEntity.ok(null);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResumeById(@PathVariable(name = "id") Long id) {
        if (service.deleteResumeById(id)) {
            return ResponseEntity.ok(service.getResumes());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resumes by id: "+id+" not found");
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editResume(@RequestBody EditResumeDto resume) {
        if (service.getResumeById(resume.getId()) != null) {
            service.editResume(resume);
            return ResponseEntity.ok(service.getResumeById(resume.getId()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resume: "+resume+" does not exists");
    }

    @PostMapping("/status/{id}")
    public ResponseEntity<?> changeResumeState(@PathVariable(name = "id") Long id) {
        if (service.getResumeById(id) != null) {
            service.changeResumeState(id);
            return ResponseEntity.ok(service.getResumeById(id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resume by id " + id + " not found");
    }
}
