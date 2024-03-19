package kg.attractor.ht49.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class RespondedApplicant {
    private Long id;
    private Long resumeId;
    private Long vacancyId;
    private Boolean confirmation;
}
