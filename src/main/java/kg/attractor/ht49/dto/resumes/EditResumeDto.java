package kg.attractor.ht49.dto.resumes;

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
    private Long id;
    private String title;
    private String categoryName;
    private Double salary;
    private List<WorkExpInfoEditDto> wei;
    private List<EducationInfoEditDto> ei;
}
