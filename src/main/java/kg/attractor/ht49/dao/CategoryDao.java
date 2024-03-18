package kg.attractor.ht49.dao;

import kg.attractor.ht49.models.Category;
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

    public Optional<Category> getCategoryByName(String name){
        String sql = """
                select * from CATEGORIES
                where name ilike ?
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql,new BeanPropertyRowMapper<>(Category.class),name)
                )
        );
    }

    public Optional<Category> getCategoryById(Long id){
        String sql = """
                select * from CATEGORIES
                where id = ?
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql,new BeanPropertyRowMapper<>(Category.class),id)
                )
        );
    }
}
