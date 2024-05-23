package kg.attractor.ht49.dto.educations;


import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import kg.attractor.ht49.dto.educations.customAnnotations.ValidDateRange;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ValidDateRange(message = "{pastEducation}")
public class CreateEducationInfoDto {
    @Size(max = 100)
    private String institution;

    @Size(max = 200)
    private String program;

    @Past(message = "{pastEducation}")
    private LocalDate startDate;

    private LocalDate endDate;

    @Size(max = 100)
    private String degree;
}
