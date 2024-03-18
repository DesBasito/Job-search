package kg.attractor.ht49.dto.resumes;

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
public class CreateResumeDto {
    private String name;
    private UserDto user;
    private CategoryDto category;
    private Double salary;
}
