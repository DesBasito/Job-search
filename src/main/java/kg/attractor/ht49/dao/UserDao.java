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

    public List<UserModel> getUsersByTypeAcc(String type) {
        String sql = """
                Select * from users u\s
                inner join PUBLIC.USER_AUTHORITY UA on UA.USER_ID = U.ID
                inner join PUBLIC.AUTHORITIES A on A.ID = UA.AUTHORITY_ID
                 where A.ROLE like ?
                """;
        return switch (type) {
            case "admin" ->
                    template.query(sql, new BeanPropertyRowMapper<>(UserModel.class), AccountTypes.ADMIN.toString());
            case "employee" ->
                    template.query(sql, new BeanPropertyRowMapper<>(UserModel.class), AccountTypes.APPLICANT.toString());
            case "employer" ->
                    template.query(sql, new BeanPropertyRowMapper<>(UserModel.class), AccountTypes.EMPLOYER.toString());
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
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
                insert into users(name, surname, age, email, password, phone_number, avatar, ENABLED)\s
                values (:name, :surname, :age, :email, :password, :phoneNumber, :avatar, :enabled);
                """;
        namedParameter.update(sql, new MapSqlParameterSource()
                .addValue("name", userModel.getName())
                .addValue("surname", userModel.getSurname())
                .addValue("age", userModel.getAge())
                .addValue("email", userModel.getEmail())
                .addValue("password", userModel.getPassword())
                .addValue("phoneNumber", userModel.getPhoneNumber())
                .addValue("avatar", userModel.getAvatar())
                .addValue("enabled", userModel.getEnabled())
        );

    }

    public List<UserModel> getUserByName(String name, AccountTypes type) {
        String sql = """
                select * from users U
                 inner join PUBLIC.USER_AUTHORITY UA on UA.USER_ID = U.ID
                 inner join PUBLIC.AUTHORITIES A on A.ID = UA.AUTHORITY_ID
                where name ilike ? and A.ROLE = ?
                """;
        if (type.toString().equalsIgnoreCase("employee")) {
            return template.query(sql, new BeanPropertyRowMapper<>(UserModel.class), name, AccountTypes.EMPLOYER.toString());
        }
        return template.query(sql, new BeanPropertyRowMapper<>(UserModel.class), name, AccountTypes.APPLICANT.toString());
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
                select * from users U
                inner join PUBLIC.USER_AUTHORITY UA on UA.USER_ID = U.ID
                inner join PUBLIC.AUTHORITIES A on A.ID = UA.AUTHORITY_ID
                where U.email ilike ? and A.ROLE = ?
                """;
        return getUserModel(email, type, sql);
    }

    public Optional<UserModel> getEmplByPhone(String phone, AccountTypes type) {
        String sql = """
                select * from users U
                 inner join PUBLIC.USER_AUTHORITY UA on UA.USER_ID = U.ID
                 inner join PUBLIC.AUTHORITIES A on A.ID = UA.AUTHORITY_ID
                where PHONE_NUMBER like ? and A.ROLE = ?
                """;
        return getUserModel(phone, type, sql);
    }

    private Optional<UserModel> getUserModel(String phone, AccountTypes type, String sql) {
        if (type.toString().equalsIgnoreCase("employee")) {
            return Optional.ofNullable(
                    DataAccessUtils.singleResult(
                            template.query(sql, new BeanPropertyRowMapper<>(UserModel.class), phone, AccountTypes.EMPLOYER.toString())
                    )
            );
        }
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql, new BeanPropertyRowMapper<>(UserModel.class), phone, AccountTypes.APPLICANT.toString())
                )
        );
    }

    public String getRoleByUserEmail(String email) {
        String sql = """
                select ROLE from AUTHORITIES a, USER_AUTHORITY ua, USERS u
                                   where ua.AUTHORITY_ID= a.ID and u.ID = ua.USER_ID
                and U.EMAIL = ?
                """;
        return template.queryForObject(sql, String.class, email);
    }

    public Long getTypeIdByName(String accType) {
        String sql = """
                select A.ID from AUTHORITIES A
                where A.ROLE like ?
                """;
        return template.queryForObject(sql, Long.class, accType);
    }

    public void createUserAuthority(Long userId, Long roleId) {
        String sql = """
                insert into USER_AUTHORITY(user_id, authority_id)\s
                values (:userId,:roleId);
                """;
        namedParameter.update(sql, new MapSqlParameterSource()
                .addValue("userId",userId)
                .addValue("roleId",roleId)
        );
    }
}
