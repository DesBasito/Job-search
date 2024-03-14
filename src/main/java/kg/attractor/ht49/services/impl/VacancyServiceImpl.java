package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dao.VacancyDao;
import kg.attractor.ht49.dto.UserDto;
import kg.attractor.ht49.dto.VacancyDto;
import kg.attractor.ht49.exceptions.CategoryNotFoundException;
import kg.attractor.ht49.exceptions.UserNotFoundException;
import kg.attractor.ht49.exceptions.VacancyNotFoundException;
import kg.attractor.ht49.models.Category;
import kg.attractor.ht49.models.Vacancy;
import kg.attractor.ht49.services.CategoryService;
import kg.attractor.ht49.services.UserService;
import kg.attractor.ht49.services.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
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
    public List<VacancyDto> getVacanciesOfRespondedApplicant(String userEmail) {
        UserDto applicant = userService.getUserByEmail(userEmail);
        Long id = applicant.getId();
        List<Vacancy> vacancies = dao.getVacanciesByRespondedApplicantsId(id);
        return getVacancyDtos(vacancies);
    }

    @Override
    public List<VacancyDto> getVacanciesOfCategory(String strip) {
        Category category = categoryService.getCategoryIdByName(strip);
        List<Vacancy> vacancies = dao.getVacancyByCategory(category.getId());
        return getVacancyDtos(vacancies);
    }

    @Override
    public VacancyDto getVacancyById(Long vacancyId) {
        try {
            Vacancy e = dao.getVacancyById(vacancyId).orElseThrow(VacancyNotFoundException::new);
            return VacancyDto.builder()
                    .id(e.getId())
                    .category(categoryService.getCategoryById(e.getCategoryId()))
                    .createdDate(e.getCreatedDate())
                    .expTo(e.getExpTo())
                    .description(e.getDescription())
                    .expFrom(e.getExpFrom())
                    .isActive(e.getIsActive())
                    .salary(e.getSalary())
                    .updateTime(e.getUpdateTime())
                    .name(e.getName())
                    .author(userService.getUserById(e.getAuthorId()))
                    .build();
        }catch (VacancyNotFoundException e){
            log.error("vacancy by id: {} not found",vacancyId);
        }
        return null;
    }


    private List<VacancyDto> getVacancyDtos(List<Vacancy> vacancies) {
        List<VacancyDto> dtos = new ArrayList<>();
        vacancies.forEach(e -> dtos.add(VacancyDto.builder()
                .id(e.getId())
                .category(categoryService.getCategoryById(e.getCategoryId()))
                .createdDate(e.getCreatedDate())
                .expTo(e.getExpTo())
                .description(e.getDescription())
                .expFrom(e.getExpFrom())
                .isActive(e.getIsActive())
                .salary(e.getSalary())
                .updateTime(e.getUpdateTime())
                .name(e.getName())
                .author(userService.getUserById(e.getAuthorId()))
                .build()));
        return dtos;
    }
}
