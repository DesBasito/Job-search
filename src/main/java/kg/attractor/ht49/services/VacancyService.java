package kg.attractor.ht49.services;


import kg.attractor.ht49.dto.vacancies.VacancyDto;
import kg.attractor.ht49.dto.vacancies.VacancyEditDto;
import kg.attractor.ht49.exceptions.CategoryNotFoundException;
import kg.attractor.ht49.exceptions.UserNotFoundException;

import java.util.List;

public interface VacancyService {
    List<VacancyDto> getAllVacancies();

    List<VacancyDto> getVacanciesOfRespondedApplicant(String email) throws UserNotFoundException;

    List<VacancyDto> getVacanciesOfCategory(String strip) throws CategoryNotFoundException;

    VacancyDto getVacancyById(Long vacancyId);

    void createVacancy(VacancyDto vacancy) ;

    Boolean deleteVacancyById(Long id);

    void editVacancy(VacancyEditDto vacancy);

    List<VacancyDto> getAllVacanciesOfCompany(Long id);

    List<VacancyDto> getActiveVacanciesOfCompany(Long id);

    List<VacancyDto> getActiveVacancies();

    VacancyDto getVacancyByName(String name);

    Long createVacancyAndReturnId(VacancyDto vacancyDto);

    void deactivateVacancy(Long id);

    void activateVacancy(Long id);
}
