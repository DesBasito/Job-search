package kg.attractor.ht49.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespondedApplicant {
    private Long id;
    private Long resumeId;
    private Long vacancyId;
    private Boolean confirmation;
}
