package kg.attractor.ht49.dto.vacancies;

import jakarta.validation.constraints.*;
import kg.attractor.ht49.dto.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacancyEditDto {
    private Long id;

    @NotBlank
    @Size(max = 30)
    private String name;

    @NotBlank
    @Size(max = 245, message = "{valid.vacancy.description}")
    private String description;

    @NotBlank
    @Size(max = 30)
    private String category;

    @DecimalMin(value = "0", message = "{valid.vacancy.experience}")
    private Double salary;

    @Max(value = 40)
    @Min(value = 0)
    @NotNull
    private Integer expFrom;

    @NotNull
    @Min(value = 0, message = "{valid.vacancy.experience}")
    @Max(value = 50, message = "{valid.vacancy.experience}")
    private Integer expTo;

    @AssertTrue(message = "{valid.vacancy.isGreater}")
    private boolean isGreater() {
        return expTo > expFrom;
    }
}
