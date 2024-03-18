package kg.attractor.ht49.dto.vacancies;

import kg.attractor.ht49.dto.CategoryDto;
import kg.attractor.ht49.dto.users.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacancyDto {
    private Long id;
    private String name;
    private String description;
    private CategoryDto category;
    private UserDto author;
    private Double salary;
    private Integer expFrom;
    private Integer expTo;
    private Boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;
}
