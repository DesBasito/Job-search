package kg.attractor.ht49.models;

import lombok.Data;

@Data
public class WorkExperienceInfo {
    private Long id;
    private Long resumeId;
    private Byte years;
    private String companyName;
    private String position;
    private String responsibilities;
}
