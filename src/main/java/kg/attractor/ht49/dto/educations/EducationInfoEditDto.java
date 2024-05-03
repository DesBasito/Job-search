package kg.attractor.ht49.dto.educations;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EducationInfoEditDto {
    private Long id;

    @NotBlank
    @Size(max = 200)
    private String institution;

    @Size(max = 500)
    private String program;

    @Past
    private LocalDate startDate;

    @Past
    private LocalDate endDate;

    @Pattern(regexp = "^[a-zA-Z]+$")
    private String degree;
}
