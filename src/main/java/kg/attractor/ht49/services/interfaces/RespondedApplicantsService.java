package kg.attractor.ht49.services.interfaces;

import kg.attractor.ht49.dto.RespondedApplicantDto;
import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.models.RespondedApplicant;

import java.util.List;

public interface RespondedApplicantsService {
    List<RespondedApplicantDto> getAllRespondents();

    List<ResumeDto> getRespondedApplicantsByVacancyId(Long id);

    void applyToVacancy(Long resumeId, Long vacancyId);


    Long ifThereResumeIdAndVacancyId(List<ResumeDto> resume,Long vacancyId);

    RespondedApplicantDto getRespondedApplicantById(Long respId);

    List<RespondedApplicantDto> getRespondentsByEmployer(Long id);

    List<RespondedApplicantDto> getRespondentsByApplicant(Long id);

    RespondedApplicant getRespondedApplicantModelById(Long respApplId);

    void checkUserByResume(RespondedApplicantDto respondedApplicant, UserDto sender);
}
