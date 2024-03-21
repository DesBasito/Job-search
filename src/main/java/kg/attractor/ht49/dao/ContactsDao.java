package kg.attractor.ht49.dao;

import kg.attractor.ht49.models.ContactsInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ContactsDao {
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedParameter;


    public void createNewContactsInfo(ContactsInfo contactsInfo) {
        String sql = """
                insert into CONTACTS_INFO(infovalue, type_id, resume_id) VALUES ( :infoValue,:typeId, :resumeId )
                """;
        namedParameter.update(sql, new MapSqlParameterSource()
                .addValue("infoValue",contactsInfo.getInfoValue())
                .addValue("typeId",contactsInfo.getTypeId())
                .addValue("resumeId",contactsInfo.getResumeId()));
    }

    public List<ContactsInfo> getContactsInfoByResumeId(Long id) {
        String sql = """
                select * from CONTACTS_INFO
                where RESUME_ID = ?;
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(ContactsInfo.class), id);
    }
}
