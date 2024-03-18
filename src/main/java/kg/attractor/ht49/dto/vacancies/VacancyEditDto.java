package kg.attractor.ht49.dto.vacancies;

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
    private String name;
    private String description;
    private CategoryDto category;
    private Double salary;
    private Integer expFrom;
    private Integer expTo;
}
