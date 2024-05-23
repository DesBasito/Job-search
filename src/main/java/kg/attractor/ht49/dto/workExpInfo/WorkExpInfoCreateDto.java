package kg.attractor.ht49.dto.workExpInfo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import kg.attractor.ht49.dto.resumes.ResumeDto;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkExpInfoCreateDto {
    @Positive @Max(value = 30)
    private Byte years;

    @Size(max = 255)
    private String companyName;

    @Size(max = 255,min = 1)
    private String position;

    @Size(max = 355)
    private String responsibilities;
}
