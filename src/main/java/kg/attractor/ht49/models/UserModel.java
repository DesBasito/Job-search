package kg.attractor.ht49.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "age")
    private Byte age;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "enabled")
    private Boolean enabled;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "applicantId")
    List<Resume> resumes;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "authorId")
    List<Vacancy> vacancies;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "sender")
    List<Message> messages;
}
