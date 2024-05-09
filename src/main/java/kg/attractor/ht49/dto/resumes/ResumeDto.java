package kg.attractor.ht49.dto.resumes;

import kg.attractor.ht49.dto.ContactInfo.ContactsInfoWithIdDto;
import kg.attractor.ht49.dto.educations.EducationInfoDto;
import kg.attractor.ht49.dto.workExpInfo.WorkExperienceInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeDto {
    private Long id;
    private String name;
    private String category;
    private String userEmail;
    private Double salary;
    private Boolean isActive;
    private LocalDateTime createdDate;
    private String updateDate;
    private List<WorkExperienceInfoDto> workExpInfo;
    private List<EducationInfoDto> educationInfo;
    private List<ContactsInfoWithIdDto> contacts;
}
