package kg.attractor.ht49.dao;

import kg.attractor.ht49.dto.vacancies.VacancyDto;
import kg.attractor.ht49.dto.vacancies.VacancyEditDto;
import kg.attractor.ht49.models.Vacancy;
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

    public void createVacancy(Vacancy vacancy) {
        String sql = """
                insert into VACANCIES(name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_date)\s
                values (:name, :description , :categoryId, :salary, :expFrom, :expTo, :isActive, :authorId, :createdDate, :updateDate);
                """;
        namedParameter.update(sql, new MapSqlParameterSource()
                .addValue("name", vacancy.getName())
                .addValue("description", vacancy.getDescription())
                .addValue("categoryId", vacancy.getCategoryId())
                .addValue("salary", vacancy.getSalary())
                .addValue("expFrom", vacancy.getExpFrom())
                .addValue("expTo", vacancy.getExpTo())
                .addValue("isActive", true)
                .addValue("authorId", vacancy.getAuthorId())
                .addValue("createdDate", LocalDateTime.now())
                .addValue("updateDate", LocalDateTime.now())
        );
    }

    public void deleteVacancyById(Long id) {
        String sql = """
                DELETE FROM VACANCIES WHERE id = ?;
                """;
        template.update(sql,id);
    }

    public void editVacancy(Vacancy vacancy) {
        String sql = """
            UPDATE VACANCIES
            SET name = :name, description = :description, category_id = :categoryId, salary = :salary, exp_from = :expFrom, exp_to = :expTo
            WHERE id = :id;
            """;
        namedParameter.update(sql, new MapSqlParameterSource()
                .addValue("name", vacancy.getName())
                .addValue("description", vacancy.getDescription())
                .addValue("categoryId", vacancy.getCategoryId())
                .addValue("salary", vacancy.getSalary())
                .addValue("expFrom", vacancy.getExpFrom())
                .addValue("expTo", vacancy.getExpTo())
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

    public Optional<Vacancy> getVacancyByName(String name) {
        String sql = """
                select * from VACANCIES
                where NAME =?
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class),name)
                )
        );
    }

    public Long createVacancyAndReturnId(Vacancy vacancy) {
        String sql = """
                insert into VACANCIES(name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_date)
                values (?, ? , ?, ?, ?, ?, ?,?,?,?);
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql,new String[]{"id"});
            ps.setString(1,vacancy.getName());
            ps.setString(2,vacancy.getDescription());
            ps.setLong(3,vacancy.getCategoryId());
            ps.setDouble(4,vacancy.getSalary());
            ps.setInt(5,vacancy.getExpFrom());
            ps.setInt(6,vacancy.getExpTo());
            ps.setBoolean(7,true);
//            ps.setLong(8,vacancy.getAuthorId());
            ps.setDate(9, Date.valueOf(LocalDate.now()));
            ps.setDate(10, Date.valueOf(LocalDate.now()));
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey().longValue());
    }



    public void changeVacancyState(Long id, boolean b) {
        String sql = """
            UPDATE VACANCIES
            SET IS_ACTIVE = :isActive,UPDATE_DATE = :update_date
            WHERE id = :id;
            """;
        namedParameter.update(sql, new MapSqlParameterSource()
                .addValue("isActive",b)
                .addValue("update_date",LocalDateTime.now())
                .addValue("id",id)
        );
    }

    public void updateVacancy(Long id) {
        String sql = """
            UPDATE VACANCIES
            SET UPDATE_DATE = :update_date
            WHERE id = :id;
            """;
        namedParameter.update(sql, new MapSqlParameterSource()
                .addValue("update_date",LocalDateTime.now())
                .addValue("id",id)
        );
    }
}
