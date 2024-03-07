package kg.attractor.ht49.models;

import lombok.Data;

@Data
public class RespondedApplicants {

    private Long id;
    private Long resumeId;
    private Long vacancyId;
    private Boolean confirmation;
}
