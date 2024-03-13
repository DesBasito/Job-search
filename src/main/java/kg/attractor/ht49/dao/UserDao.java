package kg.attractor.ht49.dao;


import kg.attractor.ht49.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedParameter;

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM USERS";
        return template.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public Optional<User> getUserByEmail(String email) {
        String sql = """
                select * from users
                where email ilike ?
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql, new BeanPropertyRowMapper<>(User.class), email)
                )
        );
    }

    public void createUser(User user) {
        String sql = """
                insert into users(name, surname, age, email, password, phone_number, avatar, acc_type)\s
                values (:name, :surname, :age, :email, :password, :phoneNumber, :avatar, :accType);
                """;
        namedParameter.update(sql, new MapSqlParameterSource()
                .addValue("name", user.getName())
                .addValue("surname", user.getSurname())
                .addValue("age", user.getAge())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword())
                .addValue("phoneNumber", user.getPhoneNumber())
                .addValue("avatar", user.getAvatar())
                .addValue("accType", user.getAccType())
        );

    }

    public List<User> getUserByName(String name) {
        String sql = """
                select * from users
                where name ilike ?
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(User.class), name);
    }

    public Optional<User> getUserByPhone(String phone) {
        String sql = """
                select * from users
                where PHONE_NUMBER ilike ?
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql, new BeanPropertyRowMapper<>(User.class), phone)
                )
        );
    }

    public List<User> getAllUsersByVacancyId(Long id) {
        String sql = """
                SELECT u.*
                FROM users u
                INNER JOIN responded_applicants r ON u.id = r.resume_id
                WHERE r.vacancy_id = ?
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(User.class), id);
    }

    public Optional<User> getUserById(Long id) {
        String sql = """
                select * from users
                where ID = ?
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql, new BeanPropertyRowMapper<>(User.class), id)
                )
        );
    }
}
