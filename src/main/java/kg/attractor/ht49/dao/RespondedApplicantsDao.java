package kg.attractor.ht49.dao;


import kg.attractor.ht49.models.RespondedApplicant;
import kg.attractor.ht49.models.Resume;
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

    public List<Resume> getAllRespApplByVacancyId(Long id) {
        String sql = """
                select * from RESUMES
                inner join RESPONDED_APPLICANTS RA on RESUMES.ID = RA.RESUME_ID
                where RA.VACANCY_ID = ?
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Resume.class),id);
    }
}
