package kg.attractor.ht49.dao;

import kg.attractor.ht49.models.Message;
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
public class MessagesDao {
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Message> getMessagesByRespApplId(Long id){
        String sql= """
                select * from MESSAGES
                where RESPONDED_APPLICANT_ID = ?
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Message.class),id);
    }

    public List<Message> getNewMessages(Long lastMessage,Long respId){
        String sql= """
                select * from MESSAGES
                where id > ? and RESPONDED_APPLICANT_ID = ?
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Message.class),lastMessage,respId);
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

    public void createMessage(Message build) {
        String sql = """
                insert into MESSAGES (SENDER, CONTENT, TIMESTAMP,RESPONDED_APPLICANT_ID) \s
                values (:sender,:content ,:timestamp,:responded_applicant_id);
                """;
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("sender",build.getSender())
                .addValue("content",build.getContent())
                .addValue("timestamp",build.getTimestamp())
//                .addValue("responded_applicant_id",build.getRespApplId())
        );
    }
}
