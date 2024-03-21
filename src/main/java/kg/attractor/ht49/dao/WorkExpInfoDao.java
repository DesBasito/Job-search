package kg.attractor.ht49.dao;

import kg.attractor.ht49.dto.educations.CreateEducationInfoDto;
import kg.attractor.ht49.dto.educations.EducationInfoDto;
import kg.attractor.ht49.dto.workExpInfo.WorkExpInfoCreateDto;
import kg.attractor.ht49.models.EducationInfo;
import kg.attractor.ht49.models.WorkExperienceInfo;
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
public class WorkExpInfoDao {
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedParameter;

    public Optional<WorkExperienceInfo> getWorkExpByResumeId(Long id) {
        String sql = """
                select * from WORK_EXPERIENCE_INFO
                where RESUME_ID = ?;
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql, new BeanPropertyRowMapper<>(WorkExperienceInfo.class), id)
                )
        );
    }

    public void editInfo(WorkExperienceInfo info) {
        String sql = """
            UPDATE WORK_EXPERIENCE_INFO
            SET YEARS  = :years, COMPANY_NAME = :companyName, POSITION = :position, RESPONSIBILITIES = :responsibilities
            WHERE id = :id;
            """;
        namedParameter.update(sql, new MapSqlParameterSource()
                .addValue("years",info.getYears())
                .addValue("companyName", info.getCompanyName())
                .addValue("position", info.getPosition())
                .addValue("responsibilities",info.getResponsibilities())
                .addValue("id",info.getId())
        );
    }

    public void deleteWorkExpInfoById(Long id) {
        String sql = """
                DELETE FROM WORK_EXPERIENCE_INFO WHERE id = ?;
                """;
        template.update(sql, id);
    }

    public void createWorkInfo(WorkExpInfoCreateDto info, Long id) {
        String sql = """
                insert into WORK_EXPERIENCE_INFO(resume_id, years, company_name, position, responsibilities)
                values (:resumeId, :years, :companyName,  :position, :responsibilities);
                """;
        namedParameter.update(sql, new MapSqlParameterSource()
                .addValue("resumeId", id)
                .addValue("years", info.getYears())
                .addValue("companyName", info.getCompanyName())
                .addValue("position", info.getPosition())
                .addValue("responsibilities", info.getResponsibilities())
        );
    }


    public Optional<EducationInfo> getEducationById(Long id) {
        String sql = """
                 select * from EDUCATION_INFO
                where ID = ?;
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql, new BeanPropertyRowMapper<>(EducationInfo.class), id)
                )
        );
    }

    public Long createAndReturnId(WorkExpInfoCreateDto info, Long id) {
        String sql = """
                 insert into WORK_EXPERIENCE_INFO(resume_id, years, company_name, position, responsibilities)
                values (?,?,?,?,?);
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, id);
            ps.setByte(2, info.getYears());
            ps.setString(3, info.getCompanyName());
            ps.setString(4, info.getPosition());
            ps.setString(5, info.getResponsibilities());
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey().longValue());
    }

    public List<WorkExperienceInfo> getListWorkExpByResumeId(Long resumeId) {
        String sql = """
                select * FROM WORK_EXPERIENCE_INFO WHERE RESUME_ID = ?;
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(WorkExperienceInfo.class),resumeId);
    }
}
