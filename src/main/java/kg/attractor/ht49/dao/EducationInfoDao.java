package kg.attractor.ht49.dao;

import kg.attractor.ht49.dto.educations.CreateEducationInfoDto;
import kg.attractor.ht49.dto.educations.EducationInfoDto;
import kg.attractor.ht49.dto.educations.EducationInfoEditDto;
import kg.attractor.ht49.models.EducationInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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
public class EducationInfoDao {
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedParameter;

    public List<EducationInfo> getEducationByResume(Long id) {
        String sql = """
                select * from EDUCATION_INFO
                where RESUME_ID = ?;
                """;
       return template.query(sql, new BeanPropertyRowMapper<>(EducationInfo.class), id);
    }

    public void editInfo(EducationInfo info) {
        String sql = """
            UPDATE EDUCATION_INFO
            SET INSTITUTION  = :institution, PROGRAM = :program, START_DATE = :startDate, END_DATE = :endDate,DEGREE= :degree
            WHERE id = :id;
            """;
        namedParameter.update(sql, new MapSqlParameterSource()
                .addValue("institution",info.getInstitution())
                .addValue("program", info.getProgram())
                .addValue("startDate", info.getStartDate())
                .addValue("endDate",info.getEndDate())
                .addValue("degree",info.getDegree())
                .addValue("id",info.getId())
        );
    }

    public void deleteEducationInfoById(Long id) {
        String sql = """
                DELETE FROM EDUCATION_INFO WHERE id = ?;
                """;
        template.update(sql,id);
    }

    public void createEducationInfo(CreateEducationInfoDto info,Long resumeId) {
        String sql = """
                insert into EDUCATION_INFO(institution, program, resume_id, start_date, end_date, degree)\s
                values (:institution, :program, :resumeId, :startDate, :endDate, :degree);
                """;
        namedParameter.update(sql, new MapSqlParameterSource()
                .addValue("institution",info.getInstitution())
                .addValue("program",info.getProgram())
                .addValue("resumeId",resumeId)
                .addValue("startDate",info.getStartDate())
                .addValue("endDate", info.getEndDate())
                .addValue("degree", info.getDegree())
        );
    }


    public Optional<EducationInfo> getEducationById(Long id) {
        String sql = """
                 select * from EDUCATION_INFO
                where ID = ?;
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql, new BeanPropertyRowMapper<>(EducationInfo.class),id)
                )
        );
    }


    public Long createAndReturnId(CreateEducationInfoDto info,Long id) {
        String sql = """
                insert into EDUCATION_INFO(institution, program, resume_id, start_date, end_date, degree)
                values (?, ? , ?, ?, ?, ?)
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql,new String[]{"id"});
            ps.setString(1,info.getInstitution());
            ps.setString(2,info.getProgram());
            ps.setLong(3,id);
            ps.setDate(4, info.getStartDate());
            ps.setDate(5, info.getEndDate());
            ps.setString(6,info.getDegree());
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey().longValue());
    }
}
