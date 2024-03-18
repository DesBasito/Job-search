package kg.attractor.ht49.services;

import kg.attractor.ht49.dto.resumes.CreateResumeDto;
import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.exceptions.CategoryNotFoundException;
import kg.attractor.ht49.exceptions.UserNotFoundException;
import kg.attractor.ht49.models.Resume;

import java.util.List;

public interface ResumeService {
    List<ResumeDto> getResumeByCategory(String name) throws CategoryNotFoundException;
    List<ResumeDto> getResumes();
    List<ResumeDto> getResumeByUserEmail(String user) throws UserNotFoundException;
    ResumeDto getResumeById(Long id);

    void createResume(CreateResumeDto resume);

    Boolean deleteResumeById(Long id);

    void editResume( ResumeDto resume);

    List<ResumeDto> getResumeDtos(List<Resume> resumes);

    List<ResumeDto> getResumeByName(String rName);
}
