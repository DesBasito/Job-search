package kg.attractor.ht49.dto.educations;


import kg.attractor.ht49.dto.resumes.ResumeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEducationInfoDto {
    private ResumeDto resume;
    private String institution;
    private String program;
    private Date startDate;
    private Date endDate;
    private String degree;
}
