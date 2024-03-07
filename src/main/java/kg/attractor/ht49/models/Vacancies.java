package kg.attractor.ht49.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Vacancies {
    private Long id;
    private String description;
    private Long categoryId;
    private Double salary;
    private Integer expFrom;
    private Integer expTo;
    private Boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;
}
