package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dto.RespondedApplicantDto;
import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.dto.vacancies.VacancyDto;
import kg.attractor.ht49.exceptions.VacancyNotFoundException;
import kg.attractor.ht49.models.RespondedApplicant;
import kg.attractor.ht49.models.Resume;
import kg.attractor.ht49.repositories.RespondedApplicantRepository;
import kg.attractor.ht49.services.interfaces.RespondedApplicantsService;
import kg.attractor.ht49.services.interfaces.ResumeService;
import kg.attractor.ht49.services.interfaces.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RespApplServiceImpl implements RespondedApplicantsService {
    private final RespondedApplicantRepository respondedApplicantRepository;
    private final ResumeService resumeService;
    private final VacancyService vacancyService;

    @Override
    public List<RespondedApplicantDto> getAllRespondents() {
        return respondedApplicantRepository.findAll().stream().map(this::getRespondedApplicantDto).collect(Collectors.toList());
    }

    @Override
    public List<ResumeDto> getRespondedApplicantsByVacancyId(Long id) {
        if (vacancyService.existsById(id)) {
            throw new VacancyNotFoundException("Vacancy by id: " + id + " not found");
        }
        List<RespondedApplicant> applicants = respondedApplicantRepository.findByVacancyId(id);
        return applicants.stream().map(a -> resumeService.getResumeDto(a.getResume())).collect(Collectors.toList());
    }

    @Override
    public void applyToVacancy(Long resumeId, Long vacancyId) {
        Assert.notNull(resumeId,"resume id must not be null");
        Assert.notNull(vacancyId,"vacancy id must not be null");
        for (RespondedApplicant res : respondedApplicantRepository.findAll()) {
            if (Objects.equals(res.getVacancy().getId(), vacancyId) && Objects.equals(res.getResume().getId(), resumeId)) {
                return;
            }
        }
        RespondedApplicant respondedApplicant = RespondedApplicant.builder()
                .resume(resumeService.getResumeModel(resumeId))
                .vacancy(vacancyService.getVacancyModelById(vacancyId))
                .confirmation(false)
                .build();
        respondedApplicantRepository.save(respondedApplicant);
    }



    @Override
    public Long ifThereResumeIdAndVacancyId(List<ResumeDto> resumes, Long vacancyId) {
        return respondedApplicantRepository.findAll().stream().filter(res -> resumes.stream().anyMatch(resume -> Objects.equals(res.getResume().getId(), resume.getId()) && Objects.equals(res.getVacancy().getId(), vacancyId))).findFirst().map(RespondedApplicant::getId).orElse(null);
    }

    @Override
    public RespondedApplicantDto getRespondedApplicantById(Long respId) {
        RespondedApplicant responded = respondedApplicantRepository.findById(respId).orElseThrow(()->new NoSuchElementException("no responded applicant found by id: "+respId));
        return getRespondedApplicantDto(responded);
    }

    @Override
    public Integer getRespondentsSizeByEmployer(String email) {

       List<VacancyDto> vacancies=  vacancyService.getActiveVacanciesByCompany(email);
       List<Resume> resumes = new ArrayList<>();
       for (VacancyDto vacancy : vacancies){
           List<RespondedApplicant> respondedApplicants = respondedApplicantRepository.findByVacancyId(vacancy.getId());
          respondedApplicants.forEach(r -> resumes.add(r.getResume()));
       }
        return resumes.size();
    }

    @Override
    public RespondedApplicant getRespondedApplicantModelById(Long respApplId) {
        return respondedApplicantRepository.findById(respApplId).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void checkUserByResume(RespondedApplicantDto respondedApplicant, UserDto sender) {
        VacancyDto vacancy = respondedApplicant.getVacancy();
        ResumeDto resume = respondedApplicant.getResume();

        String resumeOwnerEmail = resume.getUserEmail();
        String vacancyAuthorEmail = vacancy.getAuthorEmail();
        String senderEmail = sender.getEmail();

        if (!resumeOwnerEmail.equals(senderEmail) && !vacancyAuthorEmail.equals(senderEmail)) {
            throw new IllegalArgumentException("This chat page is not available for you.");
        }
    }


    private RespondedApplicantDto getRespondedApplicantDto(RespondedApplicant e) {
        return  RespondedApplicantDto.builder()
                .id(e.getId())
                .resume(resumeService.getResumeById(e.getResume().getId()))
                .vacancy(vacancyService.getVacancyById(e.getVacancy().getId()))
                .confirmation(e.getConfirmation())
                .build();
    }


}
