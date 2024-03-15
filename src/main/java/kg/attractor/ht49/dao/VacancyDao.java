package kg.attractor.ht49.dao;

import kg.attractor.ht49.dto.VacancyDto;
import kg.attractor.ht49.models.Vacancy;
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
public class VacancyDao {
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedParameter;

    public List<Vacancy> getAllVacancies() {
        String sql = """
                select * from VACANCIES;
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }

    public Optional<Vacancy> getVacancyById(Long id) {
        String sql = """
                select * from VACANCIES
                where ID =?
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), id)
                )
        );
    }

    public List<Vacancy> getVacanciesByRespondedApplicantsId(Long id) {
        String sql = """
                SELECT v.*
                FROM vacancies v
                INNER JOIN responded_applicants ra ON v.id = ra.resume_id
                WHERE ra.vacancy_id = ?
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), id);
    }

    public List<Vacancy> getVacancyByCategory(Long id) {
        String sql = """
                SELECT * FROM vacancies\s
                WHERE CATEGORY_ID = ?
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), id);
    }

    public void createVacancy(VacancyDto vacancy) {
        String sql = """
                insert into VACANCIES(name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_date)\s
                values (:name, :description , :categoryId, :salary, :expFrom, :expTo, :isActive, :authorId, :createdDate, :updateDate);
                """;
        namedParameter.update(sql, new MapSqlParameterSource()
                .addValue("name", vacancy.getName())
                .addValue("description", vacancy.getDescription())
                .addValue("categoryId", vacancy.getCategory().getId())
                .addValue("salary", vacancy.getSalary())
                .addValue("expFrom", vacancy.getExpFrom())
                .addValue("expTo", vacancy.getExpTo())
                .addValue("isActive", true)
                .addValue("authorId", vacancy.getAuthor().getId())
                .addValue("createdDate", LocalDateTime.now())
                .addValue("updateDate", LocalDateTime.now())
        );
    }

    public void deleteVacancyById(Long id) {
        String sql = """
                DELETE FROM VACANCIES WHERE id = :id;
                """;
        namedParameter.update(sql,new MapSqlParameterSource("id",id));
    }

    public void editVacancy(VacancyDto vacancy) {
        String sql = """
            UPDATE VACANCIES
            SET name = :name, description = :description, category_id = :categoryId, salary = :salary, exp_from = :expFrom, exp_to = :expTo, is_active = :isActive, update_date = :updateDate
            WHERE id = :id;
            """;
        namedParameter.update(sql, new MapSqlParameterSource()
                .addValue("name", vacancy.getName())
                .addValue("description", vacancy.getDescription())
                .addValue("categoryId", vacancy.getCategory().getId())
                .addValue("salary", vacancy.getSalary())
                .addValue("expFrom", vacancy.getExpFrom())
                .addValue("expTo", vacancy.getExpTo())
                .addValue("isActive", vacancy.getIsActive())
                .addValue("updateDate", LocalDateTime.now())
                .addValue("id",vacancy.getId())
        );
    }

    public List<Vacancy> getVacanciesOfCompany(Long id) {
        String sql = """
                SELECT * FROM vacancies
                WHERE AUTHOR_ID = ?
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), id);
    }

    public List<Vacancy> getActiveVacanciesOfCompany(Long id) {
        String sql = """
                SELECT * FROM vacancies
                WHERE AUTHOR_ID = ? and IS_ACTIVE = true;
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), id);
    }

    public List<Vacancy> getActiveVacancies() {
        String sql = """
                SELECT * FROM vacancies
                WHERE IS_ACTIVE = true;
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }
}
