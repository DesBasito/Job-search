package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dto.VacancyDto;
import kg.attractor.ht49.services.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    @Override
    public List<VacancyDto> getAllVacancies() {
        return null;
    }
}
