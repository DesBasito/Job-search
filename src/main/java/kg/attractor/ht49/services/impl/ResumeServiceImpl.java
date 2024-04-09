package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dao.ResumeDao;
import kg.attractor.ht49.dto.educations.EducationInfoForFrontDto;
import kg.attractor.ht49.dto.resumes.ResumeCreateDto;
import kg.attractor.ht49.dto.resumes.EditResumeDto;
import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.dto.workExpInfo.WorkExpInfoForFrontDto;
import kg.attractor.ht49.exceptions.CategoryNotFoundException;
import kg.attractor.ht49.exceptions.ResumeNotFoundException;
import kg.attractor.ht49.exceptions.UserNotFoundException;
import kg.attractor.ht49.models.Category;
import kg.attractor.ht49.models.Resume;
import kg.attractor.ht49.services.interfaces.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
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
            throw new CategoryNotFoundException("Category: " + categoryName + " not found");
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
    public List<ResumeDto> getResumesByUserEmail(String email) {
        Long id = userService.getUserId(email);
        if (id == null) {
            throw new UserNotFoundException("User with email " + email + " not found");
        }
        List<Resume> resumes = dao.getAllResumesByUserId(id);
        return getResumeDtos(resumes);
    }

    @Override
    public ResumeDto getResumeById(Long id) {
        Resume r = dao.getResumeById(id).orElseThrow(() -> new ResumeNotFoundException("Resume by id " + id + " not found"));
        return ResumeDto.builder()
                .id(r.getId())
                .name(r.getName())
                .category(category.getCategoryById(r.getCategoryId()).getName())
                .userEmail(userService.getUserById(r.getApplicantId()).getEmail())
                .salary(r.getSalary())
                .isActive(r.getIsActive())
                .createdDate(r.getCreatedDate())
                .updateDate(r.getUpdateDate())
                .workExpInfo(weiService.getWorkExperiencesByResumeId(id))
                .educationInfo(eiService.getEducationsInfoByResumeId(id))
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
    public void editResume(EditResumeDto editDto, Authentication auth) {
        Category category1 = category.getCategoryByName(editDto.getCategoryName());
        if (category1 == null) {
            throw new CategoryNotFoundException("Error with filling category object");
        }
        if (dao.getResumeById(editDto.getId()).isEmpty()) {
            throw new IllegalArgumentException("Resume by id: " + editDto.getId() + " not found");
        }
        Resume resum = dao.getResumeById(editDto.getId()).orElseThrow(()->new ResumeNotFoundException("resume by id:"+editDto.getId()+" not found"));
        UserDto dto = userService.getUserById(resum.getApplicantId());
        if (!dto.getEmail().equals(auth.getName())){
            throw new IllegalArgumentException("Resume not belong to user: "+ auth.getName()+". It belongs to: "+dto.getEmail());
        }
        Resume resume = Resume.builder()
                .id(editDto.getId())
                .name(editDto.getTitle())
                .categoryId(category1.getId())
                .salary(editDto.getSalary())
                .build();
        dao.editResume(resume);
        editDto.getEducationInfo().forEach(eiService::editInfo);
        editDto.getWorkExpInfoEdit().forEach(weiService::editInfo);
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
                .updateDate(r.getUpdateDate())
                .workExpInfo(weiService.getWorkExperiencesByResumeId(r.getId()))
                .educationInfo(eiService.getEducationsInfoByResumeId(r.getId()))
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
        if (dao.getResumeById(id).isEmpty()) {
            throw new ResumeNotFoundException("Resume by id: " + id + " not found");
        }
        boolean b = !getResumeById(id).getIsActive();
        dao.changeResumeState(id, b);
    }

    @Override
    public List<WorkExpInfoForFrontDto> getWorkExpInfoByResumeId(Long id) {
        return  weiService.getWorkExperiencesByResumeId(id).stream().map(workExp -> WorkExpInfoForFrontDto.builder()
       .id(workExp.getId())
       .companyName(workExp.getCompanyName())
       .position(workExp.getPosition())
       .years(workExp.getYears())
       .responsibilities(workExp.getResponsibilities())
       .build()).collect(Collectors.toList());
    }

    @Override
    public List<EducationInfoForFrontDto> getEducationInfoByResumeId(Long id) {
        return eiService.getEducationsInfoByResumeId(id).stream().map(edu -> EducationInfoForFrontDto.builder()
                .id(edu.getId())
                .institution(edu.getInstitution())
                .program(edu.getProgram())
                .degree(edu.getDegree())
                .startDate(edu.getStartDate())
                .endDate(edu.getEndDate())
                .build()).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserByResumesAuthorEmail(String userEmail) {
        return  userService.getUserByEmail(userEmail);
    }

    @Override
    public Long createResume(ResumeCreateDto resume, Authentication auth) {
        Category category1 = category.getCategoryByName(resume.getCategoryName());
        if (category1 == null) {
            throw new CategoryNotFoundException();
        }
        UserDto dto = userService.getUserByEmail(auth.getName());
        Resume resume1 = Resume.builder()
                .name(resume.getTitle())
                .applicantId(dto.getId())
                .categoryId(category1.getId())
                .salary(resume.getSalary())
                .build();
        Long id = dao.createAndReturnResumeId(resume1);
        resume.getEducationInfo().forEach(e -> eiService.createEducationInfo(e, id));
        resume.getWorkExpInfo().forEach(e -> weiService.createWorkExpInfo(e, id));
        resume.getContacts().forEach(e -> contacts.createNewContactsInfo(e, id));
        return id;
    }

    @Override
    public Page<ResumeDto> getResumesPage(Integer page) {
        List<ResumeDto> resumes = getResumeDtos(dao.getAllResumes());
        return toPage(resumes, PageRequest.of(page, 5));

    }

    @Override
    public Page<ResumeDto> getFilteredResumesPage(Integer page,String category) {
        List<ResumeDto> resumes = getResumeDtos(dao.getAllResumesByCategory(category));
        return toPage(resumes, PageRequest.of(page, 5));
    }

    private Page<ResumeDto> toPage(List<ResumeDto> resumes, Pageable pageable){
        if (pageable.getOffset() >= resumes.size()){
            return Page.empty();
        }
        int startIndex = (int) pageable.getOffset();
        int endIndex = (int) ((pageable.getOffset() + pageable.getPageSize() > resumes.size() ?
                resumes.size() : pageable.getOffset() + pageable.getPageSize()));
        List<ResumeDto> subList = resumes.subList(startIndex, endIndex);
        return new PageImpl<>(subList, pageable, resumes.size());
    }
}
