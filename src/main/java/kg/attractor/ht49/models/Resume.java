package kg.attractor.ht49.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "resumes")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private UserModel applicant;

    private Double salary;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "resume")
    private Set<RespondedApplicant> respondedApplicants;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "resume")
    List<WorkExperienceInfo> workExperiences;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "resume")
    List<EducationInfo> educationInfoList;
}
