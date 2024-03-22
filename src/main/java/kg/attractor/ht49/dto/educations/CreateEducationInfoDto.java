package kg.attractor.ht49.dto.educations;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEducationInfoDto {
    @Pattern(regexp = "^[a-zA-Z]+$")
    @NotBlank @Size(max = 50)
    private String institution;
    @NotBlank @Size(max = 200)
    private String program;
    @Past @NotBlank
    private Date startDate;
    @Past @NotBlank
    private Date endDate;
    @Pattern(regexp = "^[a-zA-Z]+$")
    @NotBlank
    private String degree;
}
