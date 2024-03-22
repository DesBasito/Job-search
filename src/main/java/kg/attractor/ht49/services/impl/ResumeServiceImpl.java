package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dao.ResumeDao;
import kg.attractor.ht49.dto.resumes.ResumeCreateDto;
import kg.attractor.ht49.dto.resumes.EditResumeDto;
import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.enums.AccountTypes;
import kg.attractor.ht49.exceptions.CategoryNotFoundException;
import kg.attractor.ht49.exceptions.ResumeNotFoundException;
import kg.attractor.ht49.exceptions.UserNotFoundException;
import kg.attractor.ht49.models.Category;
import kg.attractor.ht49.models.Resume;
import kg.attractor.ht49.models.User;
import kg.attractor.ht49.services.*;
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
    private final EducationInfoService eiService;
    private final WorkExperienceInfoService weiService;
    private final ContactsInfoService contacts;

    @Override
    public List<ResumeDto> getResumeByCategory(String categoryName) {
        Category categoryId = category.getCategoryByName(categoryName);
        if (categoryId == null) {
            throw new CategoryNotFoundException("Category: "+categoryName+" not found");
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
    public List<ResumeDto> getResumeByUserEmail(String email) {
            Long id = userService.getUserId(email);
            if (id == null) {
                log.error("user with email {} not found", email);
                throw new UserNotFoundException("User with email "+email+" not found");
            }
            List<Resume> resumes = dao.getAllResumesByUserId(id);
            return getResumeDtos(resumes);
    }

    @Override
    public ResumeDto getResumeById(Long id) {
            Resume r = dao.getResumeById(id).orElseThrow(() -> new ResumeNotFoundException("Resume by id "+id+" not found"));
            return ResumeDto.builder()
                    .id(r.getId())
                    .name(r.getName())
                    .category(category.getCategoryById(r.getCategoryId()).getName())
                    .userEmail(userService.getUserById(r.getApplicantId()).getEmail())
                    .salary(r.getSalary())
                    .isActive(r.getIsActive())
                    .createdDate(r.getCreatedDate())
                    .updateDate(r.getUpdateTime())
                    .wei(weiService.getWorkExperiencesByResumeId(id))
                    .ei(eiService.getEducationsInfoByResumeId(id))
                    .contacts(contacts.getContactsByResumeId(id))
                    .build();
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
            Category category1 = category.getCategoryByName(editDto.getCategoryName());
            if (category1 == null){
                throw new CategoryNotFoundException("Error with filling category object");
            }
            if (dao.getResumeById(editDto.getId()).isEmpty()){
                throw new IllegalArgumentException("Resume by id: "+editDto.getId()+" not found");
            }
            Resume resume = Resume.builder()
                    .id(editDto.getId())
                    .name(editDto.getTitle())
                    .categoryId(category1.getId())
                    .salary(editDto.getSalary())
                    .build();
            dao.editResume(resume);
            editDto.getEi().forEach(eiService::editInfo);
            editDto.getWei().forEach(weiService::editInfo);
    }

    @Override
    public List<ResumeDto> getResumeDtos(List<Resume> resumes) {
        List<ResumeDto> dtos = new ArrayList<>();
        resumes.forEach(r -> dtos.add(ResumeDto.builder()
                .id(r.getId())
                .name(r.getName())
                .category(category.getCategoryById(r.getCategoryId()).getName())
                .userEmail(userService.getUserById(r.getApplicantId()).getEmail())
                .salary(r.getSalary())
                .isActive(r.getIsActive())
                .createdDate(r.getCreatedDate())
                .updateDate(r.getUpdateTime())
                .wei(weiService.getWorkExperiencesByResumeId(r.getId()))
                .ei(eiService.getEducationsInfoByResumeId(r.getId()))
                .contacts(contacts.getContactsByResumeId(r.getId()))
                .build()));
        return dtos;
    }

    @Override
    public List<ResumeDto> getResumeByName(String rName) {
        List<Resume> resumeList = dao.getResumesByName(rName);
        return getResumeDtos(resumeList);
    }


    @Override
    public void changeResumeState(Long id) {
        if (dao.getResumeById(id).isEmpty()){
            throw new ResumeNotFoundException("Resume by id: "+id+" not found");
        }
        boolean b = !getResumeById(id).getIsActive();
        dao.changeResumeState(id, b);
    }

    @Override
    public void createResume(ResumeCreateDto resume) {
            Category category1 = category.getCategoryByName(resume.getCategoryName());
            if (category1 == null){
                throw new CategoryNotFoundException();
            }
            User user = userService.getRawUserByEmail(resume.getAuthorEmail());
            if (user == null || user.getAccType().equals(AccountTypes.EMPLOYEE.toString())){
                throw new UserNotFoundException();
            }
            Resume resume1 = Resume.builder()
                    .name(resume.getTitle())
                    .applicantId(user.getId())
                    .categoryId(category1.getId())
                    .salary(resume.getSalary())
                    .build();
            Long id = dao.createAndReturnResumeId(resume1);
            resume.getEi().forEach( e -> eiService.createEducationInfo(e,id));
            resume.getWei().forEach(e -> weiService.createWorkExpInfo(e,id));
    }
}
