package kg.attractor.ht49.dao;


import kg.attractor.ht49.enums.AccountTypes;
import kg.attractor.ht49.models.UserModel;
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

    public List<UserModel> getAllUsers() {
        String sql = "SELECT * FROM USERs";
        return template.query(sql, new BeanPropertyRowMapper<>(UserModel.class));
    }

    public List<UserModel> getUsersByTypeAcc(AccountTypes type) {
        String sql = " Select * from users where ACC_TYPE like ?";
        if (type.toString().equalsIgnoreCase("employee")) {
            return template.query(sql, new BeanPropertyRowMapper<>(UserModel.class), AccountTypes.EMPLOYER.toString());
        }
        return template.query(sql, new BeanPropertyRowMapper<>(UserModel.class), AccountTypes.EMPLOYEE.toString());
    }

    public Optional<UserModel> getUserByEmail(String email) {
        String sql = """
                select * from users
                where email ilike ?
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql, new BeanPropertyRowMapper<>(UserModel.class), email)
                )
        );
    }

    public void createUser(UserModel userModel) {
        String sql = """
                insert into users(name, surname, age, email, password, phone_number, avatar, acc_type)\s
                values (:name, :surname, :age, :email, :password, :phoneNumber, :avatar, :accType);
                """;
        namedParameter.update(sql, new MapSqlParameterSource()
                .addValue("name", userModel.getName())
                .addValue("surname", userModel.getSurname())
                .addValue("age", userModel.getAge())
                .addValue("email", userModel.getEmail())
                .addValue("password", userModel.getPassword())
                .addValue("phoneNumber", userModel.getPhoneNumber())
                .addValue("avatar", userModel.getAvatar())
                .addValue("accType", userModel.getAccType())
        );

    }

    public List<UserModel> getUserByName(String name, AccountTypes type) {
        String sql = """
                select * from users
                where name ilike ? and ACC_TYPE = ?
                """;
        if (type.toString().equalsIgnoreCase("employee")) {
            return template.query(sql, new BeanPropertyRowMapper<>(UserModel.class), name, AccountTypes.EMPLOYER.toString());
        }
        return template.query(sql, new BeanPropertyRowMapper<>(UserModel.class), name, AccountTypes.EMPLOYEE.toString());
    }

    public Optional<UserModel> getUserByPhone(String phone) {
        String sql = """
                select * from users
                where PHONE_NUMBER ilike ?
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql, new BeanPropertyRowMapper<>(UserModel.class), phone)
                )
        );
    }

    public Optional<UserModel> getUserById(Long id) {
        String sql = """
                select * from users
                where ID = ?
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql, new BeanPropertyRowMapper<>(UserModel.class), id)
                )
        );
    }

    public void editUser(UserModel userModel) {
        String sql = """
                    UPDATE USERS
                SET NAME = :name, SURNAME = :surname,AGE = :age, password = :password, phone_number = :phoneNumber, avatar = :avatar
                WHERE id = :id;
                """;
        namedParameter.update(sql, new MapSqlParameterSource()
                .addValue("name", userModel.getName())
                .addValue("surname", userModel.getSurname())
                .addValue("age", userModel.getAge())
                .addValue("password", userModel.getPassword())
                .addValue("phoneNumber", userModel.getPhoneNumber())
                .addValue("avatar", userModel.getAvatar())
                .addValue("id", userModel.getId())
        );
    }

    public Optional<UserModel> getEmplByEmail(String email, AccountTypes type) {
        String sql = """
                select * from users
                where email ilike ? and ACC_TYPE = ?
                """;
        if (type.toString().equalsIgnoreCase("employee")) {
            return Optional.ofNullable(
                    DataAccessUtils.singleResult(
                            template.query(sql, new BeanPropertyRowMapper<>(UserModel.class), email, AccountTypes.EMPLOYEE.toString())
                    )
            );
        }
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql, new BeanPropertyRowMapper<>(UserModel.class), email, AccountTypes.EMPLOYER.toString())
                )
        );
    }

    public Optional<UserModel> getEmplByPhone(String phone, AccountTypes type) {
        String sql = """
                select * from users
                where PHONE_NUMBER like ? and ACC_TYPE = ?
                """;
        if (type.toString().equalsIgnoreCase("employee")) {
            return Optional.ofNullable(
                    DataAccessUtils.singleResult(
                            template.query(sql, new BeanPropertyRowMapper<>(UserModel.class), phone, AccountTypes.EMPLOYEE.toString())
                    )
            );
        }
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql, new BeanPropertyRowMapper<>(UserModel.class), phone, AccountTypes.EMPLOYER.toString())
                )
        );
    }
}
