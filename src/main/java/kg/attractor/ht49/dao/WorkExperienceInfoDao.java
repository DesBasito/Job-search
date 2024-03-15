package kg.attractor.ht49.dao;

import kg.attractor.ht49.models.WorkExperienceInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WorkExperienceInfoDao {
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

        public List<WorkExperienceInfo> getWorkExperienceInfos() {
            String sql = """
    select * from work_experience_info;
    """;
            return template.query(sql, new BeanPropertyRowMapper<>(WorkExperienceInfo.class));
        }

        public void addWorkExperienceInfo(WorkExperienceInfo workExperienceInfo) {
            String sql = "INSERT INTO work_experience_info (resume_id, years, company_name, position, responsibilities) " +
                    "VALUES (:resume_id, :years, :company, :position,  :resp)";
            SqlParameterSource paramSource = new MapSqlParameterSource()
                    .addValue("resume_id", workExperienceInfo.getResumeId())
                    .addValue("years", workExperienceInfo.getYears())
                    .addValue("company", workExperienceInfo.getCompanyName())
                    .addValue("position", workExperienceInfo.getPosition())
                    .addValue("resp", workExperienceInfo.getResponsibilities());
            namedParameterJdbcTemplate.update(sql, paramSource);
        }

        public void updateWorkExperienceInfo(WorkExperienceInfo workExperienceInfo) {
            String sql = "UPDATE work_experience_info" +
                    " SET COMPANY_NAME = :company, position = :position, " +
                    "YEARS= :start_date , responsibilities = :resp" +
                    " WHERE id = :id";
            SqlParameterSource paramSource = new MapSqlParameterSource()
                    .addValue("company", workExperienceInfo.getCompanyName())
                    .addValue("position", workExperienceInfo.getPosition())
                    .addValue("years", workExperienceInfo.getYears())
                    .addValue("resp", workExperienceInfo.getResponsibilities())
                    .addValue("id", workExperienceInfo.getId());
            namedParameterJdbcTemplate.update(sql, paramSource);
        }

        public void deleteWorkExperienceInfoById(Long id) {
            String sql = "DELETE FROM work_experience_info " +
                    "WHERE id = :id";
            SqlParameterSource paramSource = new MapSqlParameterSource().addValue("id", id);
            namedParameterJdbcTemplate.update(sql, paramSource);
        }

        public List<WorkExperienceInfo> getWorkExperienceInfoByResumeId(Long resumeId) {
            String sql = "SELECT * FROM work_experience_info " +
                    "WHERE resume_id = :resume_id";
            return template.query(sql, new BeanPropertyRowMapper<>(WorkExperienceInfo.class), resumeId);
        }
}
