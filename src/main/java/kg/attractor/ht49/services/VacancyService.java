package kg.attractor.ht49.services;


import kg.attractor.ht49.dto.vacancies.VacancyDto;
import kg.attractor.ht49.dto.vacancies.VacancyEditDto;
import kg.attractor.ht49.exceptions.CategoryNotFoundException;
import kg.attractor.ht49.exceptions.UserNotFoundException;

import java.util.List;

public interface VacancyService {
    List<VacancyDto> getAllVacancies();

    List<VacancyDto> getVacanciesByRespondedApplicant(String email) throws UserNotFoundException;

    List<VacancyDto> getVacanciesByCategory(String strip) throws CategoryNotFoundException;

    VacancyDto getVacancyById(Long vacancyId);

    Boolean deleteVacancyById(Long id);

    void editVacancy(VacancyEditDto vacancy);

    List<VacancyDto> getAllVacanciesByCompany(Long id);

    List<VacancyDto> getActiveVacanciesByCompany(Long id);

    List<VacancyDto> getActiveVacancies();

    VacancyDto getVacancyByName(String name);

    Long createVacancyAndReturnId(VacancyDto vacancyDto);

    void changeVacancyState(Long id);
}
