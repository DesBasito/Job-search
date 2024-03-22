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
    @NotBlank @Size(max = 30)
    private String name;
    @NotBlank @Size(max = 245,message = "description should contains not more then 245 characters")
    private String description;
    @NotBlank @Size(max = 30)
    private String category;
    @Pattern(regexp = "^\\d+$",message = "enter only digits characters")
    private Double salary;
    @Max(value = 40)
    @Min(value = 0)
    private Integer expFrom;
    @Min(value = 0,message = "enter only rational numbers")
    @Max(value = 50,message = "enter only rational numbers")
    private Integer expTo;
}
