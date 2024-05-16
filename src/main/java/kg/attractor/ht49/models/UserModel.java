package kg.attractor.ht49.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;


@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class UserModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String name;

    @Lob
    private String surname;

    private Integer age;

    @Lob
    private String email;

    @Lob
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Lob
    @Column
    private String avatar;

    @Column
    private Boolean enabled;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Authority role;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "applicant")
    List<Resume> resumes;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "author")
    List<Vacancy> vacancies;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "sender")
    List<Message> messages;}
