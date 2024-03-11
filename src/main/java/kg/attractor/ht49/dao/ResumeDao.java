package kg.attractor.ht49.dao;

import kg.attractor.ht49.exceptions.ResumeNotFoundException;
import kg.attractor.ht49.models.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

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
}
