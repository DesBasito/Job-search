package kg.attractor.ht49.services;

import kg.attractor.ht49.dto.VacancyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface VacancyService {
    List<VacancyDto> getAllVacancies();
}
