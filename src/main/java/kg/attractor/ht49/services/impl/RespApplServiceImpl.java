package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dao.RespondedApplicantsDao;
import kg.attractor.ht49.dto.RespondedApplicantDto;
import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.exceptions.ResumeNotFoundException;
import kg.attractor.ht49.exceptions.UserNotFoundException;
import kg.attractor.ht49.models.RespondedApplicant;
import kg.attractor.ht49.models.Resume;
import kg.attractor.ht49.models.User;
import kg.attractor.ht49.services.RespondedApplicantsService;
import kg.attractor.ht49.services.ResumeService;
import kg.attractor.ht49.services.UserService;
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
    private final UserService userService;

    @Override
    public List<RespondedApplicantDto> getAllRespondents() {
        List<RespondedApplicant> applicants = dao.getAllRespAppl();
        return getRespondedApplicantDtos(applicants);
    }

    @Override
    public List<ResumeDto> getRespondedApplicantsByVacancyId(Long id) {
        List<Resume> applicants = dao.getAllRespApplByVacancyId(id);
        return resumeService.getResumeDtos(applicants);
    }

    @Override
    public void ApplyToVacancy(Long resumeId, Long vacancyId) throws ResumeNotFoundException {
        ResumeDto resume = resumeService.getResumeById(resumeId);
        if (resume == null || !resume.getIsActive()){
            throw new ResumeNotFoundException("resume by id " + resumeId + " not found");
        }
        dao.createRespAppl(resume.getId(),vacancyId);
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
