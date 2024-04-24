package kg.attractor.ht49.models;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Message {
    private Long id;
    private Long respApplId;
    private Long sender;
    private String content;
    private LocalDateTime timestamp;
}
