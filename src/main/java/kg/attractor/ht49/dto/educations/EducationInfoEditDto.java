package kg.attractor.ht49.dto.educations;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EducationInfoEditDto {
    private Long id;

    @Size(max = 200)
    private String institution;

    @Size(max = 500)
    private String program;

    @Past(message = "{pastEducation}")
    private LocalDate startDate;

    private LocalDate endDate;

    @Size(max = 100)
    private String degree;

    private Boolean delete;
}
