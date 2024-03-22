package kg.attractor.ht49.dto.resumes;

import jakarta.validation.constraints.*;
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
    @NotBlank @Size(max = 45)
    private String title, categoryName;
    @NotBlank @Email
    private String authorEmail;
    @PositiveOrZero
    private Double salary;
    private List<WorkExpInfoCreateDto> wei;
    private List<CreateEducationInfoDto> ei;
    private List<ContactsInfoDto> contacts;
}
