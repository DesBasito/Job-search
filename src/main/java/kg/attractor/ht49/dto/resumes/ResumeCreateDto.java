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
    @NotBlank @NotNull @Size(max = 45)
    private String title;

    @NotBlank @NotNull @Size(max = 45)
    private String categoryName;

    @DecimalMin(value = "0",message = "{valid.vacancy.experience}")
    private Double salary;

    private List<@Valid WorkExpInfoCreateDto> workExpInfo;
    private List<@Valid CreateEducationInfoDto> educationInfo;

    @NotNull(message = "{fill.contacts}")
    private List<@Valid ContactsInfoDto> contacts;
}
