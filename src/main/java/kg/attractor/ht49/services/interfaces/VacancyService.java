package kg.attractor.ht49.services.interfaces;


import kg.attractor.ht49.dto.vacancies.VacancyCreateDto;
import kg.attractor.ht49.dto.vacancies.VacancyDto;
import kg.attractor.ht49.dto.vacancies.VacancyEditDto;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface VacancyService {
    List<VacancyDto> getAllVacancies();

    List<VacancyDto> getVacanciesByRespondedApplicant(String email) ;

    List<VacancyDto> getVacanciesByCategory(String strip) ;

    VacancyDto getVacancyById(Long vacancyId);

    Boolean deleteVacancyById(Long id);

    void editVacancy(VacancyEditDto vacancy, Authentication authentication);

    List<VacancyDto> getAllVacanciesByCompany(String email);

    List<VacancyDto> getActiveVacanciesByCompany(String email);

    List<VacancyDto> getActiveVacancies();

    VacancyDto getVacancyByName(String name);

    Long createVacancyAndReturnId(VacancyCreateDto vacancyDto, Authentication auth);

    void changeVacancyState(Long id);

    Page<VacancyDto> getActiveVacanciesPage(Integer page);
}
