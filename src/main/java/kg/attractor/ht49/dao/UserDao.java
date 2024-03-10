package kg.attractor.ht49.dao;

import kg.attractor.ht49.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate template;

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        return template.query(sql, new BeanPropertyRowMapper<>(User.class));
    }
}
