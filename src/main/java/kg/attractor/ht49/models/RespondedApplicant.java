package kg.attractor.ht49.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "responded_applicants")
public class RespondedApplicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "resume_id")
    private Resume resume;

    @JoinColumn(name = "vacancy_id")
    private Vacancy vacancyId;

    @Column(name = "confirmation")
    private Boolean confirmation;
}
