package kg.attractor.ht49.dao;

import kg.attractor.ht49.models.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class UserDao {
  private final JdbcTemplate template;

  public List<User> getAllUsers(){
      String sql = "SELECT * FROM USERS";
      return template.query(sql, new BeanPropertyRowMapper<>(User.class));
  }

  public Optional<User> getUserById(int id){
      String sql = """
              select * from users
              where id =?
              """;
      return Optional.ofNullable(
              DataAccessUtils.singleResult(
                      template.query(sql,new BeanPropertyRowMapper<>(User.class),id)
              )
      );

  }
}
