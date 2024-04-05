package kg.attractor.ht49.services.interfaces;

import kg.attractor.ht49.dto.educations.EducationInfoForFrontDto;
import kg.attractor.ht49.dto.resumes.ResumeCreateDto;
import kg.attractor.ht49.dto.resumes.EditResumeDto;
import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.dto.workExpInfo.WorkExpInfoForFrontDto;
import kg.attractor.ht49.models.Resume;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ResumeService {
    List<ResumeDto> getResumeByCategory(String name) ;
    List<ResumeDto> getResumes();
    List<ResumeDto> getResumesByUserEmail(String user);
    ResumeDto getResumeById(Long id);

    Long createResume(ResumeCreateDto resume, Authentication auth);

    Boolean deleteResumeById(Long id);

    void editResume(EditResumeDto editDto, Authentication auth);

    List<ResumeDto> getResumeDtos(List<Resume> resumes);

    List<ResumeDto> getResumeByName(String rName);

    void changeResumeState(Long id);

    List<WorkExpInfoForFrontDto> getWorkExpInfoByResumeId(Long id);

    List<EducationInfoForFrontDto> getEducationInfoByResumeId(Long id);

    UserDto getUserByResumesAuthorEmail(String userEmail);
}
