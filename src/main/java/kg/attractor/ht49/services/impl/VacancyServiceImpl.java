package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.dto.vacancies.VacancyCreateDto;
import kg.attractor.ht49.dto.vacancies.VacancyDto;
import kg.attractor.ht49.dto.vacancies.VacancyEditDto;
import kg.attractor.ht49.exceptions.UserNotFoundException;
import kg.attractor.ht49.exceptions.VacancyNotFoundException;
import kg.attractor.ht49.models.Category;
import kg.attractor.ht49.models.UserModel;
import kg.attractor.ht49.models.Vacancy;
import kg.attractor.ht49.repositories.VacancyRepository;
import kg.attractor.ht49.services.interfaces.CategoryService;
import kg.attractor.ht49.services.interfaces.UserService;
import kg.attractor.ht49.services.interfaces.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class VacancyServiceImpl implements VacancyService {
    private final VacancyRepository vacancyRepository;
    private final CategoryService categoryService;
    private final UserService userService;

    @Override
    public List<VacancyDto> getAllVacancies() {
        List<Vacancy> vacancies = vacancyRepository.findAll();
        List<VacancyDto> dtos = new ArrayList<>();
        vacancies.forEach(e -> dtos.add(getVacancyDto(e)));
        return dtos;
    }

    @Override
    public List<VacancyDto> getVacanciesByRespondedApplicant(String userEmail) {
        UserDto applicant = userService.getUserByEmail(userEmail);
        Long id = applicant.getId();
        List<Vacancy> vacancies = vacancyRepository.findVacanciesByRespondedApplicantsId(id);
        List<VacancyDto> dtos = new ArrayList<>();
        vacancies.forEach(e -> dtos.add(getVacancyDto(e)));
        return dtos;
    }

    @Override
    public List<VacancyDto> getVacanciesByCategory(String strip) {
        Category category = categoryService.getCategoryByName(strip);
        List<Vacancy> vacancies = vacancyRepository.findByCategory(category);
        List<VacancyDto> dtos = new ArrayList<>();
        vacancies.forEach(e -> dtos.add(getVacancyDto(e)));
        return dtos;
    }

    @Override
    public VacancyDto getVacancyById(Long vacancyId) {
        Vacancy e = vacancyRepository.findById(vacancyId).orElseThrow(() -> new VacancyNotFoundException("Vacancy by id" + vacancyId + " not found"));
        return getVacancyDto(e);
    }

    public void createVacancy(VacancyDto vacancy1) {
        Vacancy vacancy = Vacancy.builder()
                .name(vacancy1.getName())
                .description(vacancy1.getDescription())
                .category(categoryService.getCategoryByName(vacancy1.getCategory()))
                .author(userService.getUserModelByEmail(vacancy1.getAuthorEmail()))
                .salary(vacancy1.getSalary())
                .expFrom(vacancy1.getExpFrom())
                .expTo(vacancy1.getExpTo())
                .build();
        vacancyRepository.save(vacancy);
    }

    @Override
    public Boolean deleteVacancyById(Long id) {
        if (vacancyRepository.existsById(id)) {
            vacancyRepository.deleteById(id);
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
                .category(categoryService.getCategoryByName(vacancy.getCategory()))
                .salary(vacancy.getSalary())
                .expFrom(vacancy.getExpFrom())
                .expTo(vacancy.getExpTo())
                .build();
        vacancyRepository.save(vac);
    }

    @Override
    public List<VacancyDto> getAllVacanciesByCompany(String email) {
        UserDto userDto = userService.getUserByEmail(email);
        List<Vacancy> vacancies = vacancyRepository.findByAuthor_Email(userDto.getEmail());
        return getVacancyDtos(userDto.getId(), vacancies);
    }

    @Override
    public List<VacancyDto> getActiveVacanciesByCompany(String email) {
        Long id = userService.getUserByEmail(email).getId();
        List<Vacancy> vacancies = vacancyRepository.findByAuthor_EmailAndIsActive(email,true);
        return getVacancyDtos(id, vacancies);
    }

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
        List<Vacancy> vacancyList = vacancyRepository.findByIsActive(true);
        List<VacancyDto> dtos = new ArrayList<>();
        vacancyList.forEach(e -> dtos.add(getVacancyDto(e)));
        return dtos;
    }

    @Override
    public VacancyDto getVacancyByName(String name) {
        Vacancy e = vacancyRepository.findByName(name).orElseThrow(() -> new VacancyNotFoundException("vacancy by name " + name + " not found"));
        return getVacancyDto(e);
    }

    @Override
    public Long createVacancyAndReturnId(VacancyCreateDto vacancy1, Authentication auth) {
        Vacancy vacancy = Vacancy.builder()
                .name(vacancy1.getName())
                .description(vacancy1.getDescription())
                .category(categoryService.getCategoryByName(vacancy1.getCategory()))
                .author(userService.getUserModelByEmail(auth.getName()))
                .salary(vacancy1.getSalary())
                .expFrom(vacancy1.getExpFrom())
                .expTo(vacancy1.getExpTo())
                .build();
        return vacancyRepository.save(vacancy).getId();
    }

    @Override
    public void changeVacancyState(Long id) {
        if (!vacancyRepository.existsById(id)) {
            throw new VacancyNotFoundException("Vacancy by id " + id + " not found");
        }
        boolean b = !getVacancyById(id).getIsActive();
        vacancyRepository.changeVacancyState(id, b);
    }

    @Override
    public Page<VacancyDto> getActiveVacanciesPage(Integer pageNumber) {
        List<VacancyDto> vacancies = vacancyRepository.findByIsActive(true).stream().map(this::getVacancyDto).toList();
        return toPage(vacancies, PageRequest.of(pageNumber, 5));

    }

    @Override
    public void updateVacancy(Long id) {
        vacancyRepository.updateVacancy(id);
    }

    @Override
    public boolean existsById(Long id) {
        return vacancyRepository.existsById(id);
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
                .category(categoryService.getCategoryById(e.getCategory().getId()).getName())
                .createdDate(e.getCreatedDate())
                .expTo(e.getExpTo())
                .description(e.getDescription())
                .expFrom(e.getExpFrom())
                .isActive(e.getIsActive())
                .salary(e.getSalary())
                .updateTime(e.getUpdateDate())
                .name(e.getName())
                .authorEmail(userService.getUserById(e.getAuthor().getId()).getEmail())
                .build();
    }
}
