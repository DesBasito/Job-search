package kg.attractor.ht49.dao;


import kg.attractor.ht49.models.RespondedApplicant;
import kg.attractor.ht49.models.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class RespondedApplicantsDao {
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedParam;

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

    public void createRespAppl(Long resumeId, Long vacancyId) {
        String sql = """
                insert into RESPONDED_APPLICANTS(vacancy_id, resume_id, confirmation)
                values (:vacancyId,:resumeId,:confirmation)
                """;
        namedParam.update(sql, new MapSqlParameterSource()
                .addValue("vacancyId",vacancyId)
                .addValue("resumeId",resumeId)
                .addValue("confirmation",false));
    }

    public Long createAndReturnRespApplId(Long resumeId, Long vacancyId) {
        String sql = """
                insert into RESPONDED_APPLICANTS(vacancy_id, resume_id, confirmation)
                values (?,?,?)
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql,new String[]{"id"});
            ps.setLong(1,vacancyId);
            ps.setLong(2,resumeId);
            ps.setBoolean(3,false);
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey().longValue());
    }



}
