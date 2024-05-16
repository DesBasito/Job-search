package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dto.users.UserDto;
import kg.attractor.ht49.dto.vacancies.VacancyCreateDto;
import kg.attractor.ht49.dto.vacancies.VacancyDto;
import kg.attractor.ht49.dto.vacancies.VacancyEditDto;
import kg.attractor.ht49.exceptions.UserNotFoundException;
import kg.attractor.ht49.exceptions.VacancyNotFoundException;
import kg.attractor.ht49.models.Category;
import kg.attractor.ht49.models.Vacancy;
import kg.attractor.ht49.repositories.VacancyRepository;
import kg.attractor.ht49.services.interfaces.CategoryService;
import kg.attractor.ht49.services.interfaces.UserService;
import kg.attractor.ht49.services.interfaces.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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


    @Override
    public Boolean deleteVacancyById(Long id) {
        if (vacancyRepository.existsById(id)) {
            vacancyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public void editVacancy(VacancyEditDto vacancy) {
        Vacancy vacancy1 = vacancyRepository.findById(vacancy.getId()).orElseThrow(VacancyNotFoundException::new);
        Vacancy vac = Vacancy.builder()
                .id(vacancy.getId())
                .name(vacancy.getName())
                .description(vacancy.getDescription())
                .category(categoryService.getCategoryByName(vacancy.getCategory()))
                .salary(vacancy.getSalary())
                .author(vacancy1.getAuthor())
                .expFrom(vacancy.getExpFrom())
                .expTo(vacancy.getExpTo())
                .updateDate(LocalDateTime.now())
                .createdDate(vacancy1.getCreatedDate())
                .isActive(true)
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
        List<Vacancy> vacancies = vacancyRepository.findByAuthor_EmailAndIsActive(email, true);
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
    public Long createVacancyAndReturnId(VacancyCreateDto vacancy1, String email) {
        Vacancy vacancy = Vacancy.builder()
                .name(vacancy1.getName())
                .description(vacancy1.getDescription())
                .category(categoryService.getCategoryByName(vacancy1.getCategory()))
                .author(userService.getUserModelByEmail(email))
                .salary(vacancy1.getSalary())
                .expFrom(vacancy1.getExpFrom())
                .expTo(vacancy1.getExpTo())
                .isActive(true)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
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
    public Page<VacancyDto> getActiveVacanciesPage(Integer pageNumber, String filter) {
        final int count = 5;
        Pageable pageable ;
        Page<Vacancy> vacancies;
        if (filter != null) {
            if (filter.contains("Date")) {
                pageable = getPageableBySort(filter, pageNumber,"updateDate");
            } else {
                pageable = getPageableBySort(filter,pageNumber,"respondedApplicants");
            }
        } else {
            pageable = PageRequest.of(pageNumber,count);
        }
        vacancies = vacancyRepository.findAll(pageable);
        return vacancies.map(this::getVacancyDto);
    }


    private Pageable getPageableBySort(String filter, int pageNumber,String sort) {
        Sort sortBy;
        if (filter.contains("asc")) {
            sortBy = Sort.by(Sort.Order.asc(sort));
            return PageRequest.of(pageNumber, 5, sortBy);
        } else {
            sortBy = Sort.by(Sort.Order.desc(sort));
            return PageRequest.of(pageNumber, 5, sortBy);
        }
    }

    @Transactional
    @Override
    public void updateVacancy(Long id) {
        vacancyRepository.updateVacancyByIdAndUpdateDate(id,LocalDateTime.now());
    }

    @Override
    public boolean existsById(Long id) {
        return vacancyRepository.existsById(id);
    }

    @Override
    public Vacancy getVacancyModelById(Long vacancyId) {
        return vacancyRepository.findById(vacancyId).orElseThrow(() -> new VacancyNotFoundException("vacancy by id: " + vacancyId + " not found"));
    }

    @Override
    public Page<VacancyDto> getActiveVacanciesPageByEmail(Integer page, String email) {
        Pageable pageable =PageRequest.of(page,3);
        Page<Vacancy> vacancies= vacancyRepository.findByAuthor_EmailAndIsActive(email,true,pageable);
        return vacancies.map(this::getVacancyDto);
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
                .updateTime(e.getUpdateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .name(e.getName())
                .authorEmail(userService.getUserById(e.getAuthor().getId()).getEmail())
                .build();
    }
}
