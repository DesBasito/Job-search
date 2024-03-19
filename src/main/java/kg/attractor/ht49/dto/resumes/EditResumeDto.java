package kg.attractor.ht49.dto.resumes;

import kg.attractor.ht49.dto.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditResumeDto {
    private Long id;
    private String name;
    private CategoryDto category;
    private Double salary;
}
