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
    @Size(max = 100)
    private String institution;

    @Size(max = 200)
    private String program;

    @Past
    private Date startDate;

    @Past
    private Date endDate;

    @Pattern(regexp = "^[a-zA-Z]+$")
    private String degree;
}
