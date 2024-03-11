package kg.attractor.ht49.services;

import kg.attractor.ht49.dto.ResumeDto;
import kg.attractor.ht49.exceptions.CategoryNotFoundException;

import java.util.List;

public interface ResumeService {
    List<ResumeDto> getResumeByCategory(String name) throws CategoryNotFoundException;
    List<ResumeDto> getResumes();
}
