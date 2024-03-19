package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dao.VacancyDao;
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
@Slf4j
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
        Category category = categoryService.getCategoryIdByName(strip);
        List<Vacancy> vacancies = dao.getVacancyByCategory(category.getId());
        List<VacancyDto> dtos = new ArrayList<>();
        vacancies.forEach(e -> dtos.add(getVacancyDto(e)));
        return dtos;
    }

    @Override
    public VacancyDto getVacancyById(Long vacancyId) {
        try {
            Vacancy e = dao.getVacancyById(vacancyId).orElseThrow(VacancyNotFoundException::new);
            return getVacancyDto(e);
        } catch (VacancyNotFoundException e) {
            log.error("vacancy by id: {} not found", vacancyId);
        }
        return null;
    }

    @Override
    public void createVacancy(VacancyDto vacancy1){
        Vacancy vacancy = new Vacancy();
        vacancy.setName(vacancy1.getName());
        vacancy.setDescription(vacancy1.getDescription());
        vacancy.setCategoryId(vacancy1.getCategory().getId());
        vacancy.setAuthorId(vacancy1.getAuthor().getId());
        vacancy.setSalary(vacancy1.getSalary());
        vacancy.setExpFrom(vacancy1.getExpFrom());
        vacancy.setExpTo(vacancy1.getExpTo());
        dao.createVacancy(vacancy);
    }

    @Override
    public Boolean deleteVacancyById(Long id) {
        if (dao.getVacancyById(id).isPresent()){
            dao.deleteVacancyById(id);
            return true;
        }
        return false;
    }

    @Override
    public void editVacancy(VacancyEditDto vacancy) {
        dao.editVacancy(vacancy);
    }

    @Override
    public List<VacancyDto> getAllVacanciesByCompany(Long id) {
        List<Vacancy> vacancies = dao.getVacanciesOfCompany(id);
        try {
            if (userService.getUserById(id) == null) {
                throw new UserNotFoundException();
            }
            List<VacancyDto> dtos = new ArrayList<>();
            vacancies.forEach(e -> dtos.add(getVacancyDto(e)));
            return dtos;

        } catch (UserNotFoundException e) {
            log.error("Author by id: {} not found", id);
        }
        return null;
    }

    @Override
    public List<VacancyDto> getActiveVacanciesByCompany(Long id) {

        List<Vacancy> vacancies = dao.getActiveVacanciesOfCompany(id);
        try {
            if (userService.getUserById(id) == null) {
                throw new UserNotFoundException();
            }
            List<VacancyDto> dtos = new ArrayList<>();
            vacancies.forEach(e -> dtos.add(getVacancyDto(e)));
            return dtos;

        } catch (UserNotFoundException e) {
            log.error("Author by id: {} not found", id);
        }
        return null;
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
        try {
            Vacancy e = dao.getVacancyByName(name).orElseThrow(VacancyNotFoundException::new);
            return getVacancyDto(e);
        }catch (VacancyNotFoundException e){
            log.error("Vacancy by name {} not found",name);
            return null;
        }
    }

    @Override
    public Long createVacancyAndReturnId(VacancyDto vacancy) {
        return dao.createVacancyAndReturnId(vacancy);
    }

    @Override
    public void changeVacancyState(Long id) {
        boolean b = !getVacancyById(id).getIsActive();
        dao.changeVacancyState(id,b);
    }
    private VacancyDto getVacancyDto(Vacancy e) {
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
    }
}
