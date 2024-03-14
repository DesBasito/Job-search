package kg.attractor.ht49.services;

import kg.attractor.ht49.dto.RespondedApplicantDto;

import java.util.List;

public interface RespondedApplicantsService {
    List<RespondedApplicantDto> getAllRespondents();

    List<RespondedApplicantDto> getRespondedApplicantsByVacancyId(Long id);
}
