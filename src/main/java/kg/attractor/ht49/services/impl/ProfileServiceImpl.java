package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.dto.users.EditUserDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.dto.vacancies.VacancyDto;
import kg.attractor.ht49.services.interfaces.ProfileService;
import kg.attractor.ht49.services.interfaces.ResumeService;
import kg.attractor.ht49.services.interfaces.UserService;
import kg.attractor.ht49.services.interfaces.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final UserService service;
    private final ResumeService resumeService;
    private final VacancyService vacancyService;

    @Override
    public void editProfile(EditUserDto user) {
        service.editUser(user);
    }

    @Override
    public UserDto getUserInfoByEmail(String email) {
        return service.getUserByEmail(email);
    }

    @Override
    public List<ResumeDto> getApplicantResumes(String email) {
        return resumeService.getResumesByUserEmail(email);
    }

    @Override
    public List<VacancyDto> getEmployerVacancies(String email) {
        UserDto user = service.getUserByEmail(email);
        return vacancyService.getAllVacanciesByCompany(user.getId());
    }
}
