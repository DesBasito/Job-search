package kg.attractor.ht49.dao;

import kg.attractor.ht49.dto.EducationInfoDto;
import kg.attractor.ht49.models.EducationInfo;
import kg.attractor.ht49.models.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EducationInfoDao {
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedParameter;

    public Optional<EducationInfo> getEducationByResume(Long id) {
        String sql = """
                select * from EDUCATION_INFO
                where RESUME_ID = ?;
                """;
       return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql, new BeanPropertyRowMapper<>(EducationInfo.class), id)
                )
        );
    }

    public void editInfo(EducationInfoDto info) {
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

    public void createEducationInfo(EducationInfo info) {
        String sql = """
                insert into EDUCATION_INFO(institution, program, resume_id, start_date, end_date, degree)\s
                values (:institution, :program, :resumeId, :startDate, :endDate, :degree);
                """;
        namedParameter.update(sql, new MapSqlParameterSource()
                .addValue("institution",info.getInstitution())
                .addValue("program",info.getProgram())
                .addValue("resumeId",info.getResumeId())
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
}
