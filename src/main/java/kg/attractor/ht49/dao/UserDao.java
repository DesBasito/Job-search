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
                .addValue("userId", userId)
                .addValue("roleId", roleId)
        );
    }
}
