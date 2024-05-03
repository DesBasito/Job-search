package kg.attractor.ht49.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "parent_id")
    private Long parentCategory;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "category")
    List<Resume> resumes;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "category")
    List<Vacancy> vacancies;
}
