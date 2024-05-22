package kg.attractor.ht49.dto.vacancies;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacancyCreateDto {
    @NotBlank
    @Size(max = 30)
    private String name;

    @NotBlank @NotNull @Size(max = 245,message = "{valid.vacancy.description}")
    private String description;

    @NotBlank
    private String category;

    @DecimalMin(value = "0",message = "{valid.vacancy.experience}")
    private Double salary;

    @Max(value = 40)
    @Min(value = 0)
    private Integer expFrom;

    @Min(value = 0,message = "{valid.vacancy.experience}")
    @Max(value = 50,message = "{valid.vacancy.experience}")
    private Integer expTo;

    @AssertTrue(message = "{valid.vacancy.isGreater}")
    private boolean isGreater() {
        return expTo > expFrom;
    }
}
