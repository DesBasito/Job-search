package kg.attractor.ht49.controllers;

import kg.attractor.ht49.dto.educations.CreateEducationInfoDto;
import kg.attractor.ht49.dto.educations.EducationInfoDto;
import kg.attractor.ht49.models.EducationInfo;
import kg.attractor.ht49.services.EducationInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("education")
public class EducationInfoController {
    private final EducationInfoService service;

    @GetMapping()
    public ResponseEntity<List<EducationInfoDto>> getAllEducationsInfo(){
        return ResponseEntity.ok(service.getEducationsInfo());
    }
    @GetMapping("/resume/{id}")
    public ResponseEntity<?> getEducationInfoByResumeId(@PathVariable(name = "id") Long id) {
        EducationInfoDto education = service.getEducationByResumeId(id);
        return education == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Info by resume id: " + id + " not found") : ResponseEntity.ok(education);
    }

    @PostMapping()
    public ResponseEntity<Long> createEducationInfo(CreateEducationInfoDto info) {
        service.createEducationInfo(info);
        Long id = service.createAndReturnEduInfoId(info);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEducationInfoById(@PathVariable Long id) {
        if (service.deleteEducationInfoById(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/edit")
    public HttpStatus editEducationInfo(@RequestBody EducationInfoDto info) {
        service.editInfo(info);
        return HttpStatus.OK;
    }
}
