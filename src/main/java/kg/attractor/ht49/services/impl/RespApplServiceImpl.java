package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dao.RespondedApplicantsDao;
import kg.attractor.ht49.dto.RespondedApplicantDto;
import kg.attractor.ht49.models.RespondedApplicant;
import kg.attractor.ht49.services.RespondedApplicantsService;
import kg.attractor.ht49.services.ResumeService;
import kg.attractor.ht49.services.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RespApplServiceImpl implements RespondedApplicantsService {
    private final RespondedApplicantsDao dao;
    private final ResumeService resumeService;
    private final VacancyService vacancyService;

    @Override
    public List<RespondedApplicantDto> getAllRespondents() {
        List<RespondedApplicant> applicants = dao.getAllRespAppl();
        return getRespondedApplicantDtos(applicants);
    }

    @Override
    public List<RespondedApplicantDto> getRespondedApplicantsByVacancyId(Long id) {
        List<RespondedApplicant> applicants = dao.getAllRespApplByVacancyId(id);
        return getRespondedApplicantDtos(applicants);
    }


    private List<RespondedApplicantDto> getRespondedApplicantDtos(List<RespondedApplicant> applicants) {
        List<RespondedApplicantDto> dtos = new ArrayList<>();
        applicants.forEach(e -> dtos.add(RespondedApplicantDto.builder()
                        .id(e.getId())
                        .resume(resumeService.getResumeById(e.getResumeId()))
                        .vacancy(vacancyService.getVacancyById(e.getVacancyId()))
                        .confirmation(e.getConfirmation())
                .build()));
        return dtos;
    }

}
