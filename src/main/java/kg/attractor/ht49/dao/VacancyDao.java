package kg.attractor.ht49.dao;

import kg.attractor.ht49.models.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VacancyDao {
    private final JdbcTemplate template;

    public List<Vacancy> getAllVacancies(){
        String sql = """
                select * from VACANCIES;
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }

    public Optional<Vacancy> getVacancyById(Long id){
        String sql = """
              select * from VACANCIES
              where ID =?
              """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql,new BeanPropertyRowMapper<>(Vacancy.class),id)
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
}
