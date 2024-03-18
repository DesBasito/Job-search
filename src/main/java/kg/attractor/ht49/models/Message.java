package kg.attractor.ht49.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Message {
    private Long id;
    private Long respondedApplicants;
    private String content;
    private LocalDateTime timestamp;
}
