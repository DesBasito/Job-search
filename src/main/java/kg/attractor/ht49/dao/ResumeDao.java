package kg.attractor.ht49.dao;

import kg.attractor.ht49.exceptions.ResumeNotFoundException;
import kg.attractor.ht49.models.Resume;
import kg.attractor.ht49.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ResumeDao {
    private final JdbcTemplate template;

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
}
