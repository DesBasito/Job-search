package kg.attractor.ht49.services;

import kg.attractor.ht49.dto.RespondedApplicantDto;
import kg.attractor.ht49.dto.ResumeDto;

import java.util.List;

public interface RespondedApplicantsService {
    List<RespondedApplicantDto> getAllRespondents();

    List<ResumeDto> getRespondedApplicantsByVacancyId(Long id);
}
