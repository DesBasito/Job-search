package kg.attractor.ht49.dto.resumes;

import kg.attractor.ht49.dto.users.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeCreateDto {
    private String title;
    private String authorEmail;
    private UserDto user;
    private String categoryName;
    private Double salary;
}
