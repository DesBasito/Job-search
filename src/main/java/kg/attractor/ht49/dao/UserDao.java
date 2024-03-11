package kg.attractor.ht49.dao;

import kg.attractor.ht49.models.Category;
import kg.attractor.ht49.models.Resume;
import kg.attractor.ht49.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class UserDao {
  private final JdbcTemplate template;
  private final NamedParameterJdbcTemplate namedParameter;

  public List<User> getAllUsers(){
      String sql = "SELECT * FROM USERS";
      return template.query(sql, new BeanPropertyRowMapper<>(User.class));
  }

  public Optional<User> getUserByEmail(String email){
      String sql = """
              select * from users
              where email =?
              """;
      return Optional.ofNullable(
              DataAccessUtils.singleResult(
                      template.query(sql,new BeanPropertyRowMapper<>(User.class),email)
              )
      );

  }

    public void createUser(User user) {
        String sql = """
                insert into users(name, surname, age, email, password, phone_number, avatar, acc_type)\s
                values (:name, :surname, :age, :email, :password, :phoneNumber, :avatar, :accType);
                """;
        namedParameter.update(sql, new MapSqlParameterSource()
                .addValue("name",user.getName())
                .addValue("surname",user.getSurname())
                .addValue("age",user.getAge())
                .addValue("email",user.getEmail())
                .addValue("password",user.getPassword())
                .addValue("phoneNumber",user.getPhoneNumber())
                .addValue("avatar",user.getAvatar())
                .addValue("accType",user.getAccType())
        );

    }

    public List<User> getUserByName(String name) {
        String sql = """
              select * from users
              where name = ?
              """;
        return template.query(sql,new BeanPropertyRowMapper<>(User.class),name);
    }

    public Optional<User> getUserByPhone(String phone) {
        String sql = """
              select * from users
              where PHONE_NUMBER = ?
              """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql,new BeanPropertyRowMapper<>(User.class),phone)
                )
        );
    }

    public Optional<User> getUserId(String user) {
        String[] parts = user.split(" ");

        String name = parts[0];
        String surname = parts[1];

        String sql = """
                select * from users
                where name = ? and surname = ?
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql,new BeanPropertyRowMapper<>(User.class),name,surname)
                )
        );
    }
}
