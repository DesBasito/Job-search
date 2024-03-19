package kg.attractor.ht49.dao;

import kg.attractor.ht49.dto.resumes.CreateResumeDto;
import kg.attractor.ht49.dto.resumes.EditResumeDto;
import kg.attractor.ht49.dto.resumes.ResumeDto;
import kg.attractor.ht49.exceptions.ResumeNotFoundException;
import kg.attractor.ht49.models.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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

    public void createResume(Resume resume) {
        String sql = """
                insert into RESUMES(name, category_id, applicant_id, salary, is_active, created_date, update_date)\s
                values (:name, :categoryId, :applicantId, :salary, :isActive, :createdDate, :updateTime);
                """;
        namedParameter.update(sql, new MapSqlParameterSource()
                .addValue("name", resume.getName())
                .addValue("categoryId", resume.getCategoryId())
                .addValue("applicantId", resume.getApplicantId())
                .addValue("salary", resume.getSalary())
                .addValue("isActive", true)
                .addValue("createdDate", LocalDateTime.now())
                .addValue("updateTime", LocalDateTime.now())
        );
    }

    public void deleteResumeById(Long id) {
        String sql = """
                DELETE FROM RESUMES WHERE id = ?;
                """;
        template.update(sql,id);
    }

    public void editResume(Resume resume) {
        String sql = """
            UPDATE Resumes
            SET name = :name, category_id = :categoryId, salary = :salary, update_date = :updateTime
            WHERE id = :id;
            """;
        namedParameter.update(sql, new MapSqlParameterSource()
                .addValue("name",resume.getName())
                .addValue("categoryId", resume.getCategoryId())
                .addValue("salary", resume.getSalary())
                .addValue("updateTime", LocalDateTime.now())
                .addValue("id",resume.getId())
        );
    }

    public List<Resume> getResumesByName(String rName) {
       String sql = """
               SELECT * FROM resumes WHERE name ilike  '%' || ? || '%';
               """;
      return template.query(sql, new BeanPropertyRowMapper<>(Resume.class),rName);
    }

    public Long createAndReturnResumeId(CreateResumeDto resume) {
        String sql = """
                insert into RESUMES(name, category_id, applicant_id, salary, is_active, created_date, update_date)
                values (?, ? , ?, ?, ?, ?, ?)
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql,new String[]{"id"});
            ps.setString(1,resume.getName());
            ps.setLong(2,resume.getCategory().getId());
            ps.setLong(3,resume.getUser().getId());
            ps.setDouble(4,resume.getSalary());
            ps.setBoolean(5,true);
            ps.setDate(6, Date.valueOf(LocalDate.now()));
            ps.setDate(7, Date.valueOf(LocalDate.now()));
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey().longValue());
    }

    public void changeResumeState(Long id,boolean b) {
        String sql = """
            UPDATE RESUMES
            SET IS_ACTIVE = :isActive
            WHERE id = :id;
            """;
        namedParameter.update(sql, new MapSqlParameterSource()
                .addValue("isActive",b)
                .addValue("id",id)
        );
    }
}
