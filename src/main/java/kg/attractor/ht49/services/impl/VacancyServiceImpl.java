package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dao.VacancyDao;
import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.dto.vacancies.VacancyCreateDto;
import kg.attractor.ht49.dto.vacancies.VacancyDto;
import kg.attractor.ht49.dto.vacancies.VacancyEditDto;
import kg.attractor.ht49.exceptions.UserNotFoundException;
import kg.attractor.ht49.exceptions.VacancyNotFoundException;
import kg.attractor.ht49.models.Category;
import kg.attractor.ht49.models.Vacancy;
import kg.attractor.ht49.services.interfaces.CategoryService;
import kg.attractor.ht49.services.interfaces.UserService;
import kg.attractor.ht49.services.interfaces.VacancyService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public void editVacancy(VacancyEditDto vacancy, Authentication authentication) {
        Vacancy vac = Vacancy.builder()
                .id(vacancy.getId())
                .name(vacancy.getName())
                .description(vacancy.getDescription())
                .categoryId(categoryService.getCategoryByName(vacancy.getCategory()).getId())
                .salary(vacancy.getSalary())
                .expFrom(vacancy.getExpFrom())
                .expTo(vacancy.getExpTo())
                .build();

        Long id = userService.getUserByEmail(authentication.getName()).getId();
        List<Vacancy> vacancyList = dao.getVacanciesOfCompany(id);
        dao.editVacancy(vac);
    }

    @Override
    public List<VacancyDto> getAllVacanciesByCompany(String email) {
        UserDto userDto = userService.getUserByEmail(email);
        List<Vacancy> vacancies = dao.getVacanciesOfCompany(userDto.getId());
        return getVacancyDtos(userDto.getId(), vacancies);
    }

    @Override
    public List<VacancyDto> getActiveVacanciesByCompany(String email) {
        Long id = userService.getUserByEmail(email).getId();
        List<Vacancy> vacancies = dao.getActiveVacanciesOfCompany(id);
        return getVacancyDtos(id, vacancies);
    }

    @NotNull
    private List<VacancyDto> getVacancyDtos(Long id, List<Vacancy> vacancies) {
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
        Vacancy e = dao.getVacancyByName(name).orElseThrow(() -> new VacancyNotFoundException("vacancy by name " + name + " not found"));
        return getVacancyDto(e);
    }

    @Override
    public Long createVacancyAndReturnId(VacancyCreateDto vacancy1, Authentication auth) {
        Vacancy vacancy = Vacancy.builder()
                .name(vacancy1.getName())
                .description(vacancy1.getDescription())
                .categoryId(categoryService.getCategoryByName(vacancy1.getCategory()).getId())
                .authorId(userService.getUserByEmail(auth.getName()).getId())
                .salary(vacancy1.getSalary())
                .expFrom(vacancy1.getExpFrom())
                .expTo(vacancy1.getExpTo())
                .build();
        return dao.createVacancyAndReturnId(vacancy);
    }

    @Override
    public void changeVacancyState(Long id) {
        if (dao.getVacancyById(id).isEmpty()) {
            throw new VacancyNotFoundException("Vacancy by id " + id + " not found");
        }
        boolean b = !getVacancyById(id).getIsActive();
        dao.changeVacancyState(id, b);
    }

    @Override
    public Page<VacancyDto> getActiveVacanciesPage(Integer pageNumber) {
        List<VacancyDto> vacancies = dao.getActiveVacancies().stream().map(this::getVacancyDto).toList();
        return toPage(vacancies, PageRequest.of(pageNumber, 5));

    }

    private Page<VacancyDto> toPage(List<VacancyDto> vacncies, Pageable pageable) {
        if (pageable.getOffset() >= vacncies.size()) {
            return Page.empty();
        }
        int startIndex = (int) pageable.getOffset();
        int endIndex = (int) ((pageable.getOffset() + pageable.getPageSize() > vacncies.size() ?
                vacncies.size() : pageable.getOffset() + pageable.getPageSize()));
        List<VacancyDto> subList = vacncies.subList(startIndex, endIndex);
        return new PageImpl<>(subList, pageable, vacncies.size());
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
                .updateTime(e.getUpdateDate())
                .name(e.getName())
                .authorEmail(userService.getUserById(e.getAuthorId()).getEmail())
                .build();
    }
}
