package kg.attractor.ht49.dto.educations;


import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

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
    private LocalDate startDate;

    @Past
    private LocalDate endDate;

    @Size(max = 100)
    private String degree;
}
