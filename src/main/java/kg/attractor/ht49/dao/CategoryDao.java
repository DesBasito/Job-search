package kg.attractor.ht49.dao;

import kg.attractor.ht49.models.Category;
import kg.attractor.ht49.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CategoryDao {
    private final JdbcTemplate template;

    public Optional<Category> getCategoryID(String name){
        String sql = """
                select * from CATEGORIES
                where name = ?
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql,new BeanPropertyRowMapper<>(Category.class),name)
                )
        );
    }
}
