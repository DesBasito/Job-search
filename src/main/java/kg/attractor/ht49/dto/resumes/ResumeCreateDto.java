package kg.attractor.ht49.dto.resumes;

import kg.attractor.ht49.dto.ContactInfo.ContactsInfoDto;
import kg.attractor.ht49.dto.educations.CreateEducationInfoDto;
import kg.attractor.ht49.dto.workExpInfo.WorkExpInfoCreateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeCreateDto {
    private String title;
    private String authorEmail;
    private String categoryName;
    private Double salary;
    private List<WorkExpInfoCreateDto> wei;
    private List<CreateEducationInfoDto> ei;
    private List<ContactsInfoDto> contacts;
}
