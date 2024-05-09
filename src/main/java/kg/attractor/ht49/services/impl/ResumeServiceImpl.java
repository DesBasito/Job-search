package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dto.educations.CreateEducationInfoDto;
import kg.attractor.ht49.dto.educations.EducationInfoEditDto;
import kg.attractor.ht49.dto.educations.EducationInfoForFrontDto;
import kg.attractor.ht49.dto.resumes.EditResumeDto;
import kg.attractor.ht49.dto.resumes.ResumeCreateDto;
import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.dto.workExpInfo.WorkExpInfoCreateDto;
import kg.attractor.ht49.dto.workExpInfo.WorkExpInfoEditDto;
import kg.attractor.ht49.dto.workExpInfo.WorkExpInfoForFrontDto;
import kg.attractor.ht49.exceptions.CategoryNotFoundException;
import kg.attractor.ht49.exceptions.ResumeNotFoundException;
import kg.attractor.ht49.models.Category;
import kg.attractor.ht49.models.Resume;
import kg.attractor.ht49.models.UserModel;
import kg.attractor.ht49.repositories.ResumeRepository;
import kg.attractor.ht49.services.AuthAdapter;
import kg.attractor.ht49.services.interfaces.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final ResumeRepository resumeRepository;
    private final CategoryService categoryService;
    private final UserService userService;
    private final EducationInfoService eiService;
    private final WorkExperienceInfoService weiService;
    private final ContactsInfoService contacts;
    private final AuthAdapter adapter;

    @Override
    public List<ResumeDto> getResumeByCategory(String categoryName) {
        Category categoryId = categoryService.getCategoryByName(categoryName);
        if (categoryId == null) {
            throw new CategoryNotFoundException("Category: " + categoryName + " not found");
        }
        return resumeRepository.findByCategory(categoryId).stream().map(this::getResumeDto).collect(Collectors.toList());
    }

    @Override
    public List<ResumeDto> getResumeByCategory(String email, String categoryName) {
        Category category = categoryService.getCategoryByName(categoryName);
        UserModel user = userService.getUserModelByEmail(email);
        return resumeRepository.findByCategoryAndApplicant(category,user).stream().map(this::getResumeDto).collect(Collectors.toList());
    }

    @Override
    public List<ResumeDto> getResumes() {
        return resumeRepository.findAll().stream().map(this::getResumeDto).collect(Collectors.toList());
    }

    @Override
    public List<ResumeDto> getResumesByUserEmail(String email) {
        Long id = userService.getUserId(email);
        return resumeRepository.findByApplicant_Id(id).stream().map(this::getResumeDto).collect(Collectors.toList());
    }

    @Override
    public Page<ResumeDto> getResumesByAuthorEmail(String user,int page) {
        Pageable pageable =PageRequest.of(page,3);
        Page<Resume> resumes= resumeRepository.findByApplicant_EmailAndIsActive(user,true,pageable);
        return resumes.map(this::getResumeDto);
    }

    @Override
    public ResumeDto getResumeDto(Resume r) {
        return ResumeDto.builder()
                .id(r.getId())
                .name(r.getName())
                .category(categoryService.getCategoryById(r.getCategory().getId()).getName())
                .userEmail(userService.getUserById(r.getApplicant().getId()).getEmail())
                .salary(r.getSalary())
                .isActive(r.getIsActive())
                .createdDate(r.getCreatedDate())
                .updateDate(r.getUpdateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .workExpInfo(weiService.getWorkExperiencesByResumeId(r))
                .educationInfo(eiService.getEducationsInfoByResumeId(r))
                .contacts(contacts.getContactsByResumeId(r))
                .build();
    }

    @Override
    public ResumeDto getResumeById(Long id) {
        Resume r = resumeRepository.findById(id).orElseThrow(() -> new ResumeNotFoundException("Resume by id " + id + " not found"));
        return getResumeDto(r);
    }

    @Override
    public Boolean deleteResumeById(Long id) {
        if (resumeRepository.existsById(id)) {
            resumeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public void editResume(EditResumeDto editDto, String email) {
        Category category1 = categoryService.getCategoryByName(editDto.getCategoryName());
        if (category1 == null) {
            throw new CategoryNotFoundException("Error with filling category object");
        }
        if (!resumeRepository.existsById(editDto.getId())) {
            throw new IllegalArgumentException("Resume by id: " + editDto.getId() + " not found");
        }
        Resume resum = resumeRepository.findById(editDto.getId()).orElseThrow(() -> new ResumeNotFoundException("resume by id:" + editDto.getId() + " not found"));
        UserDto dto = userService.getUserById(resum.getApplicant().getId());
        UserDto usr = adapter.getAuthUser();
        UserModel userModel = userService.getUserModelByEmail(usr.getEmail());
        if (!dto.getEmail().equals(email)) {
            throw new IllegalArgumentException("Resume not belong to user: " + email + ". It belongs to: " + dto.getEmail());
        }
        Resume resume = Resume.builder()
                .id(editDto.getId())
                .applicant(userModel)
                .name(editDto.getTitle())
                .category(category1)
                .salary(editDto.getSalary())
                .updateDate(LocalDateTime.now())
                .isActive(true)
                .createdDate(resum.getCreatedDate())
                .build();
        resumeRepository.save(resume);


        if (editDto.getEducationInfo() != null) {
            editEducation(editDto.getEducationInfo(), editDto.getId());
        }
        if (editDto.getWorkExpInfoEdit() != null) {
            editWorkExperience(editDto.getWorkExpInfoEdit(), editDto.getId());
        }
    }

    private void editEducation(List<EducationInfoEditDto> info, Long resumeId) {
        Resume r = resumeRepository.findById(resumeId).orElseThrow(() -> new ResumeNotFoundException("Resume by id " + resumeId + " not found"));
        for (EducationInfoEditDto edit : info) {
            if (edit.getId() != null) {
                eiService.editInfo(edit,r);
            } else {
                CreateEducationInfoDto education = CreateEducationInfoDto.builder()
                        .degree(edit.getDegree())
                        .program(edit.getProgram())
                        .institution(edit.getInstitution())
                        .startDate(edit.getStartDate())
                        .endDate(edit.getEndDate())
                        .build();
                eiService.createEducationInfo(education, r);
            }
        }
    }

    private void editWorkExperience(List<WorkExpInfoEditDto> info, Long resumeId) {
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(ResumeNotFoundException::new);
        for (WorkExpInfoEditDto edit : info) {
            if (edit.getId() != null) {
                weiService.editInfo(edit, resume);
            } else {
                WorkExpInfoCreateDto work = WorkExpInfoCreateDto.builder()
                        .companyName(edit.getCompanyName())
                        .years(edit.getYears())
                        .position(edit.getPosition())
                        .responsibilities(edit.getResponsibilities())
                        .build();
                weiService.createWorkExpInfo(work, resume);
            }
        }
    }


    @Override
    public List<ResumeDto> getResumeByName(String rName) {
        return resumeRepository.findByName(rName).stream().map(this::getResumeDto).collect(Collectors.toList());
    }


    @Override
    public void changeResumeState(Long id) {
        if (!resumeRepository.existsById(id)) {
            throw new ResumeNotFoundException("Resume by id: " + id + " not found");
        }
        boolean b = !getResumeById(id).getIsActive();
        resumeRepository.changeResumeState(id, b);
    }

    @Override
    public List<WorkExpInfoForFrontDto> getWorkExpInfoByResumeId(Long id) {
        Resume resume = resumeRepository.findById(id).orElseThrow(ResumeNotFoundException::new);
        return weiService.getWorkExperiencesByResumeId(resume).stream().map(workExp -> WorkExpInfoForFrontDto.builder()
                .id(workExp.getId())
                .companyName(workExp.getCompanyName())
                .position(workExp.getPosition())
                .years(workExp.getYears())
                .responsibilities(workExp.getResponsibilities())
                .build()).collect(Collectors.toList());
    }

    @Override
    public List<EducationInfoForFrontDto> getEducationInfoByResumeId(Long id) {
        Resume resume = resumeRepository.findById(id).orElseThrow(ResumeNotFoundException::new);
        return eiService.getEducationsInfoByResumeId(resume).stream().map(edu -> EducationInfoForFrontDto.builder()
                .id(edu.getId())
                .institution(edu.getInstitution())
                .program(edu.getProgram())
                .degree(edu.getDegree())
                .startDate(edu.getStartDate())
                .endDate(edu.getEndDate())
                .build()).collect(Collectors.toList());
    }


    @Override
    public Long createResume(ResumeCreateDto resume, String email) {
        Category category1 = categoryService.getCategoryByName(resume.getCategoryName());
        if (category1 == null) {
            throw new CategoryNotFoundException();
        }
        UserModel user = userService.getUserModelByEmail(email);
        Resume resume1 = Resume.builder()
                .name(resume.getTitle())
                .applicant(user)
                .category(category1)
                .salary(resume.getSalary())
                .isActive(true)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        Resume resume2 = resumeRepository.save(resume1);
        Long id = resumeRepository.save(resume1).getId();
        if (resume.getEducationInfo() != null) {
            resume.getEducationInfo().forEach(e -> eiService.createEducationInfo(e, resume2));
        }
        if (resume.getWorkExpInfo() != null) {
            resume.getWorkExpInfo().forEach(e -> weiService.createWorkExpInfo(e, resume2));
        }
        if (resume.getContacts() != null) {
            resume.getContacts().forEach(e -> contacts.createNewContactsInfo(e, resume2));
        }
        return id;
    }

    @Override
    public Page<ResumeDto> getResumesPage(Integer page,String filter) {
        final int count = 5;
        Pageable pageable ;
        Page<Resume> resumes;
        if (filter != null) {
                pageable = getPageableByDate(filter, page);
        } else {
            pageable = PageRequest.of(page,count);
        }
        resumes = resumeRepository.findAll(pageable);
        return resumes.map(this::getResumeDto);
    }



    private Pageable getPageableByDate(String filter, int pageNumber) {
        Sort sortBy;
        if (filter.contains("asc")) {
            sortBy = Sort.by(Sort.Order.asc("updateDate"));
            return PageRequest.of(pageNumber, 5, sortBy);
        } else {
            sortBy = Sort.by(Sort.Order.desc("updateDate"));
            return PageRequest.of(pageNumber, 5, sortBy);
        }
    }

    @Override
    public EditResumeDto getResumeForEdit(Long id) {
        Resume r = resumeRepository.findById(id).orElseThrow(() -> new ResumeNotFoundException("Resume by id " + id + " not found"));
        return EditResumeDto.builder()
                .id(r.getId())
                .title(r.getName())
                .categoryName(categoryService.getCategoryById(r.getCategory().getId()).getName())
                .salary(r.getSalary())
                .workExpInfoEdit(weiService.getWorkExperiencesForEditByResumeId(r))
                .educationInfo(eiService.getEducationsInfoForEditByResumeId(r))
                .build();
    }

    @Transactional
    @Override
    public void updateResume(Long id) {
        resumeRepository.updateResumeByIdAndUpdateDate(id, LocalDateTime.now());
    }

    @Override
    public Resume getResumeModel(Long id) {
        return resumeRepository.findById(id).orElseThrow(() -> new ResumeNotFoundException("REsume by id " + id + " not found"));
    }
}
