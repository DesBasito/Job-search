package kg.attractor.ht49.dto.resumes;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import kg.attractor.ht49.dto.educations.EducationInfoEditDto;
import kg.attractor.ht49.dto.workExpInfo.WorkExpInfoEditDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditResumeDto {
    @Positive
    private Long id;
    @Size(max = 45)
    private String title;
    @Size(max = 45)
    private String categoryName;
    @PositiveOrZero
    private Double salary;
    private List<@Valid WorkExpInfoEditDto> workExpInfoEdit;
    private List<@Valid EducationInfoEditDto> educationInfo;
}
