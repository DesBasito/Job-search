package kg.attractor.ht49.dto.workExpInfo;

import kg.attractor.ht49.dto.resumes.ResumeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkExpInfoCreateDto {
    private Byte years;
    private String companyName;
    private String position;
    private String responsibilities;
}
