package kg.attractor.ht49.services.interfaces;


import kg.attractor.ht49.dto.vacancies.VacancyCreateDto;
import kg.attractor.ht49.dto.vacancies.VacancyDto;
import kg.attractor.ht49.dto.vacancies.VacancyEditDto;
import kg.attractor.ht49.models.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface VacancyService {
    List<VacancyDto> getAllVacancies();

    List<VacancyDto> getVacanciesByRespondedApplicant(String email) ;

    Page<VacancyDto> getVacanciesByCategory(String strip,int page) ;

    VacancyDto getVacancyById(Long vacancyId);

    Boolean deleteVacancyById(Long id);

    void editVacancy(VacancyEditDto vacancy);

    List<VacancyDto> getAllVacanciesByCompany(String email);

    List<VacancyDto> getActiveVacanciesByCompany(String email);

    List<VacancyDto> getActiveVacancies();

    VacancyDto getVacancyByName(String name);

    Long createVacancyAndReturnId(VacancyCreateDto vacancyDto, String email);

    void changeVacancyState(Long id);

    Page<VacancyDto> getActiveVacanciesPage(Integer page,String byUpdate);

    void updateVacancy(Long id);

    boolean existsById(Long id);

    Vacancy getVacancyModelById(Long vacancyId);

    Page<VacancyDto> getActiveVacanciesPageByEmail(Integer page, String email);

    Page<VacancyDto> getVacanciesBySearch(String title,Integer page);
}
