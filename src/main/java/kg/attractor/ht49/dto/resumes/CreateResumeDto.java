package kg.attractor.ht49.dto.resumes;

import kg.attractor.ht49.dto.WorkExperienceInfoDto;
import kg.attractor.ht49.dto.educations.EducationInfoDto;
import kg.attractor.ht49.models.ContactsInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateResumeDto {
    private String title;
    private String authorEmail;
    private String categoryName;
    private Double salary;
    private List<WorkExperienceInfoDto> wei;
    private List<EducationInfoDto> ei;
    private ContactsInfo contactsInfo;
}
