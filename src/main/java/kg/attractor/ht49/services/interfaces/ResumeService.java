package kg.attractor.ht49.services.interfaces;

import kg.attractor.ht49.dto.educations.EducationInfoForFrontDto;
import kg.attractor.ht49.dto.resumes.ResumeCreateDto;
import kg.attractor.ht49.dto.resumes.EditResumeDto;
import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.dto.workExpInfo.WorkExpInfoForFrontDto;
import kg.attractor.ht49.models.Resume;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ResumeService {
    List<ResumeDto> getResumeByCategory(String name);
    List<ResumeDto> getResumeByCategory(String email,String name);

    List<ResumeDto> getResumesByUserEmail(String user);
    Page<ResumeDto> getResumesByAuthorEmail(String user,int page);

    ResumeDto getResumeDto(Resume r);

    ResumeDto getResumeById(Long id);

    Long createResume(ResumeCreateDto resume, String email);

    Boolean deleteResumeById(Long id);

    void editResume(EditResumeDto editDto, String email);

    List<ResumeDto> getResumeByName(String rName);

    void changeResumeState(Long id);

    List<WorkExpInfoForFrontDto> getWorkExpInfoByResumeId(Long id);

    List<EducationInfoForFrontDto> getEducationInfoByResumeId(Long id);

    Page<ResumeDto> getResumesPage(Integer page,String filter);

    EditResumeDto getResumeForEdit(Long id);

    void updateResume(Long id);

    Resume getResumeModel(Long id);

    List<ResumeDto> getResumes();
}

