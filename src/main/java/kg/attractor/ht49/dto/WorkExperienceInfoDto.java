package kg.attractor.ht49.dto;

import kg.attractor.ht49.dto.resumes.ResumeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkExperienceInfoDto {
    private Long id;
    private ResumeDto resume;
    private Byte years;
    private String companyName;
    private String position;
    private String responsibilities;
}
