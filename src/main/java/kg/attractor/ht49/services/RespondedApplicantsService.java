package kg.attractor.ht49.services;

import kg.attractor.ht49.dto.RespondedApplicantDto;
import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.exceptions.UserNotFoundException;

import java.util.List;

public interface RespondedApplicantsService {
    List<RespondedApplicantDto> getAllRespondents();

    List<ResumeDto> getRespondedApplicantsByVacancyId(Long id);

    void ApplyToVacancy(String email, Long vacancyId) throws UserNotFoundException;
}
