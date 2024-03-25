package kg.attractor.ht49.dto.educations;

import jakarta.validation.constraints.*;
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
public class EducationInfoEditDto {
    @Positive
    private Long id;

    @Pattern(regexp = "^[a-zA-Z]+$")
    @Size(max = 50)
    private String institution;

    @Size(max = 200)
    private String program;

    @Past
    private Date startDate;

    @Past
    private Date endDate;

    @Pattern(regexp = "^[a-zA-Z]+$")
    private String degree;
}
