package kg.attractor.ht49.dto.workExpInfo;

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
    private Byte years;
    private Long resumeId;
    private String companyName;
    private String position;
    private String responsibilities;
}
