package kg.attractor.ht49.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private Long id;
    private String senderEmail;
    private String recipientEmail;
    private String content;
    private LocalDateTime timestamp;
}
