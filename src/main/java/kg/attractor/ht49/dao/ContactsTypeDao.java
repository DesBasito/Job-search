package kg.attractor.ht49.dao;

import kg.attractor.ht49.models.ContactType;
import kg.attractor.ht49.models.EducationInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ContactsTypeDao {
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedParameter;

    public Optional<ContactType> getContactTypeByName(String type) {
        String sql = """
                SELECT * from CONTACT_TYPES
                where TYPE ilike ?
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql, new BeanPropertyRowMapper<>(ContactType.class), type)
                )
        );
    }

    public Long createAndReturnId(ContactType contactType) {
        String sql = """
                insert into CONTACT_TYPES(TYPE)
                values (?);
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql,new String[]{"id"});
            ps.setString(1,contactType.getType());
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }
}
