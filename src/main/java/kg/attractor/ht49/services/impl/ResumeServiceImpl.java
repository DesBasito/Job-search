package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dao.ResumeDao;
import kg.attractor.ht49.dto.resumes.CreateResumeDto;
import kg.attractor.ht49.dto.resumes.ResumeCreateDto;
import kg.attractor.ht49.dto.resumes.EditResumeDto;
import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.exceptions.CategoryNotFoundException;
import kg.attractor.ht49.exceptions.ResumeNotFoundException;
import kg.attractor.ht49.exceptions.UserNotFoundException;
import kg.attractor.ht49.models.Category;
import kg.attractor.ht49.models.ContactsInfo;
import kg.attractor.ht49.models.Resume;
import kg.attractor.ht49.models.User;
import kg.attractor.ht49.services.CategoryService;
import kg.attractor.ht49.services.ContactsInfoService;
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
    private final ContactsInfoService contacts;

    @Override
    public List<ResumeDto> getResumeByCategory(String categoryName) throws CategoryNotFoundException {
        Category categoryId = category.getCategoryByName(categoryName);
        if (categoryId == null) {
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
            if (id == null) {
                log.error("user with email {} not found", email);
                throw new UserNotFoundException();
            }
            List<Resume> resumes = dao.getAllResumesByUserId(id);
            return getResumeDtos(resumes);
        } catch (ResumeNotFoundException e) {
            log.error("Resumes by user with email:{} not found", email);
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
        } catch (ResumeNotFoundException e) {
            log.error("Resume by id: {} not found", id);
        }
        return null;
    }

    @Override
    public void createResume(ResumeCreateDto resume) {
        Category category1 = category.getCategoryByName(resume.getCategoryName());
        Resume resume1 = Resume.builder()
                .name(resume.getTitle())
                .applicantId(resume.getUser().getId())
                .categoryId(category1.getId())
                .salary(resume.getSalary())
                .build();
        dao.createResume(resume1);
    }

    @Override
    public Boolean deleteResumeById(Long id) {
        if (dao.getResumeById(id).isPresent()) {
            dao.deleteResumeById(id);
            return true;
        }
        return false;
    }

    @Override
    public void editResume(EditResumeDto editDto) {
        Resume resume = Resume.builder()
                .id(editDto.getId())
                .name(editDto.getName())
                .categoryId(editDto.getCategory().getId())
                .salary(editDto.getSalary())
                .build();
        dao.editResume(resume);
    }

    @Override
    public List<ResumeDto> getResumeDtos(List<Resume> resumes) {
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

    @Override
    public List<ResumeDto> getResumeByName(String rName) {
        List<Resume> resumeList = dao.getResumesByName(rName);
        return getResumeDtos(resumeList);
    }

    @Override
    public Long createAndReturnIdResume(ResumeCreateDto resume) {
        Category category1 = category.getCategoryByName(resume.getCategoryName());
        Resume resume1 = Resume.builder()
                .name(resume.getTitle())
                .applicantId(resume.getUser().getId())
                .categoryId(category1.getId())
                .salary(resume.getSalary())
                .build();
        return dao.createAndReturnResumeId(resume1);
    }

    @Override
    public void changeResumeState(Long id) {
        boolean b = !getResumeById(id).getIsActive();
        dao.changeResumeState(id, b);
    }

    @Override
    public void createResumeTest(CreateResumeDto resume) {
        Category category1 = category.getCategoryByName(resume.getCategoryName());
        User user = userService.getRawUserByEmail(resume.getAuthorEmail());
        Resume resume1 = Resume.builder()
                .name(resume.getTitle())
                .applicantId(user.getId())
                .categoryId(category1.getId())
                .salary(resume.getSalary())
                .build();
        dao.createResume(resume1);

    }
}
