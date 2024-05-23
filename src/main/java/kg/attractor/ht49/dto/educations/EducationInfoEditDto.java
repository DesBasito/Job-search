package kg.attractor.ht49.dto.educations;

import jakarta.validation.constraints.*;
import kg.attractor.ht49.dto.educations.customAnnotations.ValidDateRange;
import kg.attractor.ht49.dto.educations.customAnnotations.ValidDateRangeEdition;
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
@ValidDateRangeEdition(message = "{pastEducation}")
public class EducationInfoEditDto {
    private Long id;

    @NotBlank
    @Size(max = 200)
    private String institution;

    @Size(max = 500)
    private String program;

    @Past(message = "{pastEducation}")
    private LocalDate startDate;

    private LocalDate endDate;

    @Pattern(regexp = "^[a-zA-Z]+$")
    private String degree;

    private Boolean delete;
}
