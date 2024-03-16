package kg.attractor.ht49.services;


import kg.attractor.ht49.dto.VacancyDto;
import kg.attractor.ht49.exceptions.CategoryNotFoundException;
import kg.attractor.ht49.exceptions.UserNotFoundException;
import kg.attractor.ht49.models.Vacancy;

import java.util.List;

public interface VacancyService {
    List<VacancyDto> getAllVacancies();

    List<VacancyDto> getVacanciesOfRespondedApplicant(String email) throws UserNotFoundException;

    List<VacancyDto> getVacanciesOfCategory(String strip) throws CategoryNotFoundException;

    VacancyDto getVacancyById(Long vacancyId);

    void createVacancy(Vacancy vacancy);

    Boolean deleteVacancyById(Long id);

    void editVacancy(VacancyDto vacancy);

    List<VacancyDto> getAllVacanciesOfCompany(Long id);

    List<VacancyDto> getActiveVacanciesOfCompany(Long id);

    List<VacancyDto> getActiveVacancies();
}
