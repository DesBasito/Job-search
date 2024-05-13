package kg.attractor.ht49.controllers.api;

import jakarta.validation.Valid;
import kg.attractor.ht49.dto.resumes.EditResumeDto;
import kg.attractor.ht49.dto.resumes.ResumeCreateDto;
import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.AuthAdapter;
import kg.attractor.ht49.services.interfaces.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("restResumes")
@RequiredArgsConstructor
@RequestMapping("api/resumes")
public class ResumeController {
    private final ResumeService service;
    private final AuthAdapter adapter;

    @GetMapping()
    public ResponseEntity<List<ResumeDto>> getResumes() {
        var resumes = service.getResumes();
        return ResponseEntity.ok(resumes);
    }

    @GetMapping("/paging")
    public ResponseEntity<Page<ResumeDto>> getResumes(@RequestParam(name = "page", defaultValue = "0") Integer page,
                             @RequestParam(name = "filter", defaultValue = "null") String filter){
        page = page > 0 ? page - 1 : 0;
        Page<ResumeDto> resumes = service.getResumesPage(page,filter);
        return ResponseEntity.ok(resumes);
    }

    @GetMapping("/searchByName/{resumeName}")
    public ResponseEntity<List<ResumeDto>> getResumeByName(@Valid @PathVariable(name = "resumeName") String rName) {
        List<ResumeDto> dto = service.getResumeByName(rName);
        return ResponseEntity.ok(dto);
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
        List<ResumeDto> resumes = service.getResumesByUserEmail(email.strip());
        return ResponseEntity.ok(resumes);
    }

    @PostMapping()
    public ResponseEntity<ResumeDto> createResume(@Valid @RequestBody ResumeCreateDto resume) {
        String email = adapter.getAuthUser().getEmail();
        Long id = service.createResume(resume,email);
        return ResponseEntity.ok(service.getResumeById(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResumeById(@Valid @PathVariable(name = "id") Long id) {
        if (service.deleteResumeById(id)) {
            return ResponseEntity.ok(service.getResumes());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resumes by id: " + id + " not found");
    }

    @PutMapping()
    public ResponseEntity<ResumeDto> editResume(@Valid @RequestBody EditResumeDto resume) {
        String email = adapter.getAuthUser().getEmail();
        service.editResume(resume,email);
        return ResponseEntity.ok(service.getResumeById(resume.getId()));
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<ResumeDto> changeResumeState(@Valid @PathVariable(name = "id") Long id) {
        service.changeResumeState(id);
        return ResponseEntity.ok(service.getResumeById(id));
    }
}
