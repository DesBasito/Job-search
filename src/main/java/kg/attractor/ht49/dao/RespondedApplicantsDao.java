package kg.attractor.ht49.dao;


import kg.attractor.ht49.models.RespondedApplicant;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RespondedApplicantsDao {
    private final JdbcTemplate template;

    public List<RespondedApplicant> getAllRespAppl() {
        String sql = """
                select * from RESPONDED_APPLICANTS;
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(RespondedApplicant.class));
    }

    public List<RespondedApplicant> getAllRespApplByVacancyId(Long id) {
        String sql = """
                select * from RESPONDED_APPLICANTS
                where VACANCY_ID = ?
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(RespondedApplicant.class),id);
    }
}
