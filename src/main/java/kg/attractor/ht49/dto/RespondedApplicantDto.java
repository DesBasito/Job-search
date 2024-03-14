package kg.attractor.ht49.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RespondedApplicantDto {
    private Long id;
    private ResumeDto resume;
    private VacancyDto vacancy;
    private Boolean confirmation;
}
