package kg.attractor.ht49.models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EducationInfo {
    private Long id;
    private Long resumeId;
    private String institution;
    private String program;
    private LocalDate startDate;
    private LocalDate endDate;
    private String degree;
}
