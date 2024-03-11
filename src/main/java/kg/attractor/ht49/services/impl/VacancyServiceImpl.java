package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dao.UserDao;
import kg.attractor.ht49.dao.VacancyDao;
import kg.attractor.ht49.dto.UserDto;
import kg.attractor.ht49.dto.VacancyDto;
import kg.attractor.ht49.exceptions.UserNotFoundException;
import kg.attractor.ht49.exceptions.VacancyNotFoundException;
import kg.attractor.ht49.models.User;
import kg.attractor.ht49.models.Vacancy;
import kg.attractor.ht49.services.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    private final VacancyDao dao;
    private final UserDao userDao;
    @Override
    public List<VacancyDto> getAllVacancies() {
        List<Vacancy> vacancies = dao.getAllVacancies();
        return getVacancyDtos(vacancies);
    }

    @Override
    public List<UserDto> getUsers(String name) throws VacancyNotFoundException {
        Vacancy vacancy = dao.getVacancyByName(name).orElseThrow(() ->new VacancyNotFoundException("Vacancy: "+ name+" does not exists" ));
        Long id = vacancy.getId();
        List<User> users = userDao.getAllUsersByVacancyId(id);
        List<UserDto> dtos = new ArrayList<>();
        users.forEach(e ->dtos.add(UserDto.builder()
                .id(e.getId())
                .name(e.getName())
                .surname(e.getSurname())
                .age(e.getAge())
                .email(e.getEmail())
                .phoneNumber(e.getPhoneNumber())
                .avatar(e.getAvatar())
                .accType(e.getAccType())
                .build()));
        return dtos;
    }

    @Override
    public List<VacancyDto> getVacanciesOfRespondedApplicant(String user) throws UserNotFoundException {
        User applicant = userDao.getUserId(user).orElseThrow(()->new UserNotFoundException("applicant: "+ user+" does not exists"));
        Long id = applicant.getId();
        List<Vacancy> vacancies = dao.getVacanciesByRespondedApplicantsId(id);
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
