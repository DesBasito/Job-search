package kg.attractor.ht49.dto.educations;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EducationInfoForFrontDto {
    @Positive
    private Long id;
    @Pattern(regexp = "^[a-zA-Z]+$")
    @NotBlank
    @Size(max = 50)
    private String institution;
    @NotBlank @Size(max = 200)
    private String program;
    @Past
    private LocalDate startDate;
    @Past
    private LocalDate endDate;
    @Pattern(regexp = "^[a-zA-Z]+$")
    @NotBlank
    private String degree;
}
