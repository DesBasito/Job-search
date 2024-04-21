package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dao.RespondedApplicantsDao;
import kg.attractor.ht49.dto.RespondedApplicantDto;
import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.dto.vacancies.VacancyDto;
import kg.attractor.ht49.exceptions.ResumeNotFoundException;
import kg.attractor.ht49.exceptions.VacancyNotFoundException;
import kg.attractor.ht49.models.RespondedApplicant;
import kg.attractor.ht49.models.Resume;
import kg.attractor.ht49.services.interfaces.RespondedApplicantsService;
import kg.attractor.ht49.services.interfaces.ResumeService;
import kg.attractor.ht49.services.interfaces.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
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
    public List<ResumeDto> getRespondedApplicantsByVacancyId(Long id) {
        if (vacancyService.getVacancyById(id) == null) {
            throw new VacancyNotFoundException("Vacancy by id: " + id + " not found");
        }
        List<Resume> applicants = dao.getAllRespApplByVacancyId(id);
        return resumeService.getResumeDtos(applicants);
    }

    @Override
    public void ApplyToVacancy(Long resumeId, Long vacancyId) {
        checkForExceptions(resumeId, vacancyId);
        for (RespondedApplicant res : dao.getAllRespAppl()) {
            if (Objects.equals(res.getVacancyId(), vacancyId) && Objects.equals(res.getResumeId(), resumeId)) {
                return;
            }
        }
        dao.createRespAppl(resumeId, vacancyId);
    }

    @Override
    public Long ApplyAndReturnVacancyId(Long resumeId, Long vacancyId) {
        checkForExceptions(resumeId, vacancyId);
        return dao.createAndReturnRespApplId(resumeId, vacancyId);
    }

    @Override
    public Long ifThereResumeIdAndVacancyId(List<ResumeDto> resumes, Long vacancyId) {
        return dao.getAllRespAppl().stream().filter(res -> resumes.stream().anyMatch(resume -> Objects.equals(res.getResumeId(), resume.getId()) && Objects.equals(res.getVacancyId(), vacancyId))).findFirst().map(RespondedApplicant::getId).orElse(null);
    }

    private void checkForExceptions(Long resumeId, Long vacancyId) {
        ResumeDto resume = resumeService.getResumeById(resumeId);
        VacancyDto vacancyDto = vacancyService.getVacancyById(vacancyId);
        if (resume == null || !resume.getIsActive()) {
            throw new ResumeNotFoundException("resume by id " + resumeId + " not found");
        }
        if (vacancyDto == null || !vacancyDto.getIsActive()) {
            throw new VacancyNotFoundException("vacancy by id " + resumeId + " not found");
        }
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
