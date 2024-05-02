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
@Table(name = "contact_types")
public class ContactType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String type;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "type")
    List<ContactsInfo> contactsInfoList;
}
