package kg.attractor.ht49.services;


import kg.attractor.ht49.dto.UserDto;
import kg.attractor.ht49.dto.VacancyDto;
import kg.attractor.ht49.exceptions.VacancyNotFoundException;

import java.util.List;

public interface VacancyService {
    List<VacancyDto> getAllVacancies();

    List<UserDto> getUsers(String name) throws VacancyNotFoundException;
}
