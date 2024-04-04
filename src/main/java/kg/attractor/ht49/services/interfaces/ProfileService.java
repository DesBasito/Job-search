package kg.attractor.ht49.services.interfaces;

import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.dto.users.EditUserDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.dto.vacancies.VacancyDto;

import java.util.List;

public interface ProfileService {
    void editProfile(EditUserDto user);
    UserDto getUserInfoByEmail(String email);
    List<ResumeDto> getApplicantResumes(String email);

    List<VacancyDto> getEmployerVacancies(String email);
}
