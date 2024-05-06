package kg.attractor.ht49.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "responded_applicant_id")
    private RespondedApplicant respApplicant;

    @ManyToOne
    @JoinColumn(name = "sender")
    private UserModel sender;

    @Lob
    @Column
    private String content;

    @Column
    private LocalDateTime timestamp;
}
