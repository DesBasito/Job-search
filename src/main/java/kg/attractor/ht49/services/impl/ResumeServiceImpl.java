package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dao.ResumeDao;
import kg.attractor.ht49.dao.UserDao;
import kg.attractor.ht49.dto.ResumeDto;
import kg.attractor.ht49.exceptions.CategoryNotFoundException;
import kg.attractor.ht49.exceptions.UserNotFoundException;
import kg.attractor.ht49.models.Resume;
import kg.attractor.ht49.services.CategoryService;
import kg.attractor.ht49.services.ResumeService;
import kg.attractor.ht49.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final CategoryService category;
    private final ResumeDao dao;
    private final UserService userService;

    @Override
    public List<ResumeDto> getResumeByCategory(String categoryName) throws CategoryNotFoundException {
        Long id = category.getCategoryId(categoryName);
        List<Resume> resumes = dao.getAllResumesByCategoryId(id);
        return getResumeDtos(resumes);
    }

    @Override
    public List<ResumeDto> getResumes(){
        List<Resume> resumes = dao.getAllResumes();
        return getResumeDtos(resumes);
    }

    @Override
    public List<ResumeDto> getResumeByuser(String user) throws UserNotFoundException {
        Long id = userService.getUserId(user);
        List<Resume> resumes = dao.getAllResumesByUserId(id);
        return getResumeDtos(resumes);
    }

    private List<ResumeDto> getResumeDtos(List<Resume> resumes) {
        List<ResumeDto> dtos = new ArrayList<>();
        resumes.forEach(r -> dtos.add(ResumeDto.builder()
                .id(r.getId())
                .name(r.getName())
                .categoryId(r.getCategoryId())
                .applicantId(r.getApplicantId())
                .salary(r.getSalary())
                .isActive(r.getIsActive())
                .createdDate(r.getCreatedDate())
                .updateTime(r.getUpdateTime())
                .build()));
        return dtos;
    }
}
