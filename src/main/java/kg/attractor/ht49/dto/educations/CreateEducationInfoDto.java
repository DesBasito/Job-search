package kg.attractor.ht49.dto.educations;


import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
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
    @Size(max = 100)
    private String institution;

    @Size(max = 200)
    private String program;

    @Past
    private Date startDate;

    @Past
    private Date endDate;

    @Size(max = 100)
    private String degree;
}
