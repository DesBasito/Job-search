package kg.attractor.ht49.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "contacts_info")
public class ContactsInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private ContactType type;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;

    @Lob
    @Column(name = "info_value")
    private String infoValue;
}
