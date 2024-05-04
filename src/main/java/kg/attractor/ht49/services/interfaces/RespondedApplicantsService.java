package kg.attractor.ht49.services.interfaces;

import kg.attractor.ht49.dto.RespondedApplicantDto;
import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.models.RespondedApplicant;

import java.util.List;

public interface RespondedApplicantsService {
    List<RespondedApplicantDto> getAllRespondents();

    List<ResumeDto> getRespondedApplicantsByVacancyId(Long id);

    void applyToVacancy(Long resumeId, Long vacancyId);


    Long ifThereResumeIdAndVacancyId(List<ResumeDto> resume,Long vacancyId);

    RespondedApplicantDto getRespondedApplicantById(Long respId);

    Integer getRespondentsSizeByEmployer(String email);

    RespondedApplicant getRespondedApplicantModelById(Long respApplId);
}
