package kg.attractor.ht49.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkExperienceInfo {
    private Long id;
    private Long resumeId;
    private Byte years;
    private String companyName;
    private String position;
    private String responsibilities;
}
