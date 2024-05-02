package kg.attractor.ht49.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "work_experience_info")
public class WorkExperienceInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;

    private Byte years;

    @Lob
    @Column(name = "company_name")
    private String companyName;

    @Lob
    private String position;

    @Lob
    private String responsibilities;
}
