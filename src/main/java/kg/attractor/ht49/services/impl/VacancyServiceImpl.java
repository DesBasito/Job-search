package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dao.VacancyDao;
import kg.attractor.ht49.dto.CategoryDto;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.dto.vacancies.VacancyDto;
import kg.attractor.ht49.dto.vacancies.VacancyEditDto;
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
@RequiredArgsConstructor

public class VacancyServiceImpl implements VacancyService {
    private final VacancyDao dao;
    private final CategoryService categoryService;
    private final UserService userService;

    @Override
    public List<VacancyDto> getAllVacancies() {
        List<Vacancy> vacancies = dao.getAllVacancies();
        List<VacancyDto> dtos = new ArrayList<>();
        vacancies.forEach(e -> dtos.add(getVacancyDto(e)));
        return dtos;
    }

    @Override
    public List<VacancyDto> getVacanciesByRespondedApplicant(String userEmail) {
        UserDto applicant = userService.getUserByEmail(userEmail);
        Long id = applicant.getId();
        List<Vacancy> vacancies = dao.getVacanciesByRespondedApplicantsId(id);
        List<VacancyDto> dtos = new ArrayList<>();
        vacancies.forEach(e -> dtos.add(getVacancyDto(e)));
        return dtos;
    }

    @Override
    public List<VacancyDto> getVacanciesByCategory(String strip) {
        Category category = categoryService.getCategoryByName(strip);
        List<Vacancy> vacancies = dao.getVacancyByCategory(category.getId());
        List<VacancyDto> dtos = new ArrayList<>();
        vacancies.forEach(e -> dtos.add(getVacancyDto(e)));
        return dtos;
    }

    @Override
    public VacancyDto getVacancyById(Long vacancyId) {
        Vacancy e = dao.getVacancyById(vacancyId).orElseThrow(() -> new VacancyNotFoundException("Vacancy by id" + vacancyId + " not found"));
        return getVacancyDto(e);

    }

    public void createVacancy(VacancyDto vacancy1) {
        Vacancy vacancy = Vacancy.builder()
                .name(vacancy1.getName())
                .description(vacancy1.getDescription())
                .categoryId(categoryService.getCategoryByName(vacancy1.getCategory()).getId())
                .authorId(userService.getUserByEmail(vacancy1.getAuthorEmail()).getId())
                .salary(vacancy1.getSalary())
                .expFrom(vacancy1.getExpFrom())
                .expTo(vacancy1.getExpTo())
                .build();
        dao.createVacancy(vacancy);
    }

    @Override
    public Boolean deleteVacancyById(Long id) {
        if (dao.getVacancyById(id).isPresent()) {
            dao.deleteVacancyById(id);
            return true;
        }
        return false;
    }

    @Override
    public void editVacancy(VacancyEditDto vacancy) {
        Vacancy vac = Vacancy.builder()
                .id(vacancy.getId())
                .name(vacancy.getName())
                .description(vacancy.getDescription())
                .categoryId(categoryService.getCategoryByName(vacancy.getCategory()).getId())
                .salary(vacancy.getSalary())
                .expFrom(vacancy.getExpFrom())
                .expTo(vacancy.getExpTo())
                .build();
        dao.editVacancy(vac);
    }

    @Override
    public List<VacancyDto> getAllVacanciesByCompany(Long id) {
        List<Vacancy> vacancies = dao.getVacanciesOfCompany(id);
        if (userService.getUserById(id) == null) {
            throw new UserNotFoundException("employer by id " + id + " not found");
        }
        List<VacancyDto> dtos = new ArrayList<>();
        vacancies.forEach(e -> dtos.add(getVacancyDto(e)));
        return dtos;
    }

    @Override
    public List<VacancyDto> getActiveVacanciesByCompany(Long id) {

        List<Vacancy> vacancies = dao.getActiveVacanciesOfCompany(id);
        if (userService.getUserById(id) == null) {
            throw new UserNotFoundException("employer by id " + id + " not found");
        }
        List<VacancyDto> dtos = new ArrayList<>();
        vacancies.forEach(e -> dtos.add(getVacancyDto(e)));
        return dtos;

    }

    @Override
    public List<VacancyDto> getActiveVacancies() {
        List<Vacancy> vacancyList = dao.getActiveVacancies();
        List<VacancyDto> dtos = new ArrayList<>();
        vacancyList.forEach(e -> dtos.add(getVacancyDto(e)));
        return dtos;
    }

    @Override
    public VacancyDto getVacancyByName(String name) {
        Vacancy e = dao.getVacancyByName(name).orElseThrow(()->new VacancyNotFoundException("vacancy by name "+name+" not found"));
        return getVacancyDto(e);
    }

    @Override
    public Long createVacancyAndReturnId(VacancyDto vacancy1) {
        Vacancy vacancy = Vacancy.builder()
                .name(vacancy1.getName())
                .description(vacancy1.getDescription())
                .categoryId(categoryService.getCategoryByName(vacancy1.getCategory()).getId())
                .authorId(userService.getUserByEmail(vacancy1.getAuthorEmail()).getId())
                .salary(vacancy1.getSalary())
                .expFrom(vacancy1.getExpFrom())
                .expTo(vacancy1.getExpTo())
                .build();
        return dao.createVacancyAndReturnId(vacancy);
    }

    @Override
    public void changeVacancyState(Long id) {
        if (dao.getVacancyById(id).isEmpty()){
            throw new VacancyNotFoundException("Vacancy by id "+id+" not found");
        }
        boolean b = !getVacancyById(id).getIsActive();
        dao.changeVacancyState(id, b);
    }

    private VacancyDto getVacancyDto(Vacancy e) {
        return VacancyDto.builder()
                .id(e.getId())
                .category(categoryService.getCategoryById(e.getCategoryId()).getName())
                .createdDate(e.getCreatedDate())
                .expTo(e.getExpTo())
                .description(e.getDescription())
                .expFrom(e.getExpFrom())
                .isActive(e.getIsActive())
                .salary(e.getSalary())
                .updateTime(e.getUpdateTime())
                .name(e.getName())
                .authorEmail(userService.getUserById(e.getAuthorId()).getEmail())
                .build();
    }
}
