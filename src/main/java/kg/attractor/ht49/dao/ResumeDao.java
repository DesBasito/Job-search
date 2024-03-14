package kg.attractor.ht49.dao;

import kg.attractor.ht49.dto.ResumeDto;
import kg.attractor.ht49.exceptions.ResumeNotFoundException;
import kg.attractor.ht49.models.Resume;
import kg.attractor.ht49.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ResumeDao {
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedParameter;

    public List<Resume> getAllResumesByCategoryId(Long id) {
        String sql = """
                select * from RESUMES
                where CATEGORY_ID = ?
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Resume.class), id);
    }

    public List<Resume> getAllResumes() {
        String sql = """
                select * from RESUMES
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Resume.class));
    }

    public List<Resume> getAllResumesByUserId(Long id) throws ResumeNotFoundException {
        String sql = """
                select * from RESUMES
                where APPLICANT_ID = ?
                """;
        List<Resume> resumes = template.query(sql, new BeanPropertyRowMapper<>(Resume.class), id);
        if (resumes.isEmpty()) {
            throw new ResumeNotFoundException("resumes by this user not found!");
        }
        return resumes;
    }

    public Optional<Resume> getResumeById(Long id) {
        String sql = """
                select * from RESUMES
                where id = ?
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql, new BeanPropertyRowMapper<>(Resume.class), id)
                )
        );
    }

    public void createResume(ResumeDto resume) {
        String sql = """
                insert into RESUMES(name, category_id, applicant_id, salary, is_active, created_date, update_date)\s
                values (:name, :categoryId, :applicantId, :salary, :isActive, :createdDate, :updateTime);
                """;
        namedParameter.update(sql, new MapSqlParameterSource()
                .addValue("name", resume.getName())
                .addValue("categoryId", resume.getCategory().getId())
                .addValue("applicantId", resume.getUser().getId())
                .addValue("salary", resume.getSalary())
                .addValue("isActive", true)
                .addValue("createdDate", LocalDateTime.now())
                .addValue("updateTime", LocalDateTime.now())
        );
    }

    public void deleteResumeById(Long id) {
        String sql = """
                DELETE FROM RESUMES WHERE id = :id;
                """;
        namedParameter.update(sql,new MapSqlParameterSource().addValue("id",id));
    }
}
