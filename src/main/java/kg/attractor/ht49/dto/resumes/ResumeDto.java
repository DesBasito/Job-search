package kg.attractor.ht49.dto.resumes;

import kg.attractor.ht49.dto.CategoryDto;
import kg.attractor.ht49.dto.ContactInfo.ContactsInfoWithIdDto;
import kg.attractor.ht49.dto.educations.EducationInfoDto;
import kg.attractor.ht49.dto.users.UserDto;
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
    private String userEmail;
    private CategoryDto category;
    private Double salary;
    private Boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
    private List<WorkExperienceInfoDto> wei;
    private List<EducationInfoDto> ei;
    private List<ContactsInfoWithIdDto> contacts;
}
