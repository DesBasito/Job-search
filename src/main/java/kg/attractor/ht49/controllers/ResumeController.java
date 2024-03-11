package kg.attractor.ht49.controllers;

import kg.attractor.ht49.dto.ResumeDto;
import kg.attractor.ht49.dto.UserDto;
import kg.attractor.ht49.exceptions.CategoryNotFoundException;
import kg.attractor.ht49.exceptions.UserNotFoundException;
import kg.attractor.ht49.models.Resume;
import kg.attractor.ht49.models.User;
import kg.attractor.ht49.services.ResumeService;
import kg.attractor.ht49.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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

    @GetMapping("resumes/{name}")
    public ResponseEntity<?> getResumesByCategory(@PathVariable String name) {
        try {
            List<ResumeDto> resumes = service.getResumeByCategory(name);
            return ResponseEntity.ok(resumes);
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("resumes/user")
    public ResponseEntity<?> getResumesByUser(@RequestParam(name = "user", defaultValue = "") String user) {
        try {
            String name = user.strip();
            List<ResumeDto> resumes = service.getResumeByuser(name);
            return ResponseEntity.ok(resumes);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
