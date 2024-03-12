package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dao.VacancyDao;
import kg.attractor.ht49.dto.UserDto;
import kg.attractor.ht49.dto.VacancyDto;
import kg.attractor.ht49.exceptions.CategoryNotFoundException;
import kg.attractor.ht49.exceptions.UserNotFoundException;
import kg.attractor.ht49.exceptions.VacancyNotFoundException;
import kg.attractor.ht49.models.Vacancy;
import kg.attractor.ht49.services.CategoryService;
import kg.attractor.ht49.services.UserService;
import kg.attractor.ht49.services.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    private final VacancyDao dao;
    private final CategoryService categoryService;
    private final UserService userService;
    @Override
    public List<VacancyDto> getAllVacancies() {
        List<Vacancy> vacancies = dao.getAllVacancies();
        return getVacancyDtos(vacancies);
    }

    @Override
    public List<UserDto> getRespondedApplicants(Long id) throws VacancyNotFoundException {
        Vacancy vacancy = dao.getVacancyById(id).orElseThrow(() ->new VacancyNotFoundException("Vacancy with id: "+ id+" does not exists" ));
        return userService.getAllUsersByVacancyId(vacancy.getId());
    }

    @Override
    public List<VacancyDto> getVacanciesOfRespondedApplicant(String userEmail) throws UserNotFoundException {
        UserDto applicant = userService.getUserByEmail(userEmail);
        Long id = applicant.getId();
        List<Vacancy> vacancies = dao.getVacanciesByRespondedApplicantsId(id);
        return getVacancyDtos(vacancies);
    }

    @Override
    public List<VacancyDto> getVacanciesOfCategory(String strip) throws CategoryNotFoundException {
        Long id = categoryService.getCategoryId(strip);
        List<Vacancy> vacancies = dao.getVacancyByCategory(id);
        return getVacancyDtos(vacancies);
    }


    private List<VacancyDto> getVacancyDtos(List<Vacancy> vacancies) {
        List<VacancyDto> dtos = new ArrayList<>();
        vacancies.forEach(e -> dtos.add(VacancyDto.builder()
                .id(e.getId())
                .categoryId(e.getCategoryId())
                .createdDate(e.getCreatedDate())
                .expTo(e.getExpTo())
                .description(e.getDescription())
                .expFrom(e.getExpFrom())
                .isActive(e.getIsActive())
                .salary(e.getSalary())
                .updateTime(e.getUpdateTime())
                .name(e.getName())
                .authorId(e.getAuthorId())
                .build()));
        return dtos;
    }
}
