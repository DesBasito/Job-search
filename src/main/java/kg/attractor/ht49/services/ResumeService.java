package kg.attractor.ht49.services;

import kg.attractor.ht49.dto.ResumeDto;
import kg.attractor.ht49.exceptions.CategoryNotFoundException;
import kg.attractor.ht49.exceptions.ResumeNotFoundException;
import kg.attractor.ht49.exceptions.UserNotFoundException;
import kg.attractor.ht49.models.Resume;

import java.util.List;

public interface ResumeService {
    List<ResumeDto> getResumeByCategory(String name) throws CategoryNotFoundException;
    List<ResumeDto> getResumes();
    List<ResumeDto> getResumeByUserEmail(String user) throws UserNotFoundException;
    ResumeDto getResumeById(Long id);

    void createResume(ResumeDto resume);

    void deleteResumeById(Long id);
}
