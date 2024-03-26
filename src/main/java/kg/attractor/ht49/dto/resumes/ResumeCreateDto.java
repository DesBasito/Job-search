package kg.attractor.ht49.dto.resumes;

import jakarta.validation.Valid;
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
    private String title;

    @NotBlank @Size(max = 45)
    private String categoryName;

    @PositiveOrZero
    private Double salary;

    private List<@Valid WorkExpInfoCreateDto> workExpInfo;
    private List<@Valid CreateEducationInfoDto> educationInfo;
    private List<@Valid ContactsInfoDto> contacts;
}
