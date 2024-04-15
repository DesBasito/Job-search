package kg.attractor.ht49.dao;

import kg.attractor.ht49.models.Message;
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
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MessagesDao {
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Message> getMessagesByUserId(Long id){
        String sql= """
                select * from MESSAGES
                where SENDER = ?
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Message.class),id);
    }

    public Optional<Message> getNewMessageBySenderId(Long id){
        String sql= """
                select * from MESSAGES
                where SENDER = ?
                ORDER BY TIMESTAMP DESC
                LIMIT 1
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                template.query(sql, new BeanPropertyRowMapper<>(Message.class), id)
                )
        );
    }

    public Long createAndReturnId(Message build) {
        String sql = """
                insert into MESSAGES (SENDER,RECIPIENT, CONTENT, TIMESTAMP) \s
                values (?,? ,?,?);
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql,new String[]{"id"});
            ps.setLong(1,build.getSender());
            ps.setLong(2,build.getRecipient());
            ps.setString(3,build.getContent());
            ps.setDate(4, Date.valueOf(LocalDate.now()));
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }
}
