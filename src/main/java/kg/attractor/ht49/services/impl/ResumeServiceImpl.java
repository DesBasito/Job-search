package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dao.ResumeDao;
import kg.attractor.ht49.dto.ResumeDto;
import kg.attractor.ht49.exceptions.CategoryNotFoundException;
import kg.attractor.ht49.exceptions.ResumeNotFoundException;
import kg.attractor.ht49.exceptions.UserNotFoundException;
import kg.attractor.ht49.models.Category;
import kg.attractor.ht49.models.Resume;
import kg.attractor.ht49.services.CategoryService;
import kg.attractor.ht49.services.ResumeService;
import kg.attractor.ht49.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final CategoryService category;
    private final ResumeDao dao;
    private final UserService userService;

    @Override
    public List<ResumeDto> getResumeByCategory(String categoryName) throws CategoryNotFoundException {
        Category categoryId = category.getCategoryIdByName(categoryName);
        if (categoryId == null){
            throw new CategoryNotFoundException();
        }
        List<Resume> resumes = dao.getAllResumesByCategoryId(categoryId.getId());
        return getResumeDtos(resumes);
    }

    @Override
    public List<ResumeDto> getResumes() {
        List<Resume> resumes = dao.getAllResumes();
        return getResumeDtos(resumes);
    }

    @Override
    public List<ResumeDto> getResumeByUserEmail(String email) throws UserNotFoundException {
        try {
            Long id = userService.getUserId(email);
            if (id == null){
                log.error("user with email {} not found",email);
                throw new UserNotFoundException();
            }
            List<Resume> resumes = dao.getAllResumesByUserId(id);
            return getResumeDtos(resumes);
        } catch (ResumeNotFoundException e) {
            log.error("Resumes by user with email:{} not found",email);
        }
        return null;
    }

    @Override
    public ResumeDto getResumeById(Long id) {
        try {
        Resume r = dao.getResumeById(id).orElseThrow(ResumeNotFoundException::new);
        return ResumeDto.builder()
                .id(r.getId())
                .name(r.getName())
                .category(category.getCategoryById(r.getCategoryId()))
                .user(userService.getUserById(r.getApplicantId()))
                .salary(r.getSalary())
                .isActive(r.getIsActive())
                .createdDate(r.getCreatedDate())
                .updateTime(r.getUpdateTime())
                .build();
        }catch (ResumeNotFoundException e){
            log.error("Resume by id: {} not found",id);
        }
        return null;
    }

    @Override
    public void createResume(ResumeDto resume) {
        dao.createResume(resume);
    }

    @Override
    public void deleteResumeById(Long id) {
        dao.deleteResumeById(id);
    }

    @Override
    public void editResume( ResumeDto resume) {
        dao.editResume(resume);
    }

    private List<ResumeDto> getResumeDtos(List<Resume> resumes) {
        List<ResumeDto> dtos = new ArrayList<>();
        resumes.forEach(r -> dtos.add(ResumeDto.builder()
                .id(r.getId())
                .name(r.getName())
                .category(category.getCategoryById(r.getCategoryId()))
                .user(userService.getUserById(r.getApplicantId()))
                .salary(r.getSalary())
                .isActive(r.getIsActive())
                .createdDate(r.getCreatedDate())
                .updateTime(r.getUpdateTime())
                .build()));
        return dtos;
    }
}
