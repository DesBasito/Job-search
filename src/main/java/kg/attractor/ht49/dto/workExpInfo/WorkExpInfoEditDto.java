package kg.attractor.ht49.dto.workExpInfo;

import jakarta.validation.constraints.*;
import kg.attractor.ht49.dto.resumes.ResumeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkExpInfoEditDto {
    @Positive
    private Long id;
    @PositiveOrZero
    @Max(value = 30)
    private Byte years;
    @NotBlank
    @Size(max = 255)
    private String companyName, position;
    @NotBlank @Size(max = 355)
    private String responsibilities;
}
