package kg.attractor.ht49.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Resume {
    private Long id;
    private String name;
    private Long applicantId;
    private Long categoryId;
    private Double salary;
    private Boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;
}
