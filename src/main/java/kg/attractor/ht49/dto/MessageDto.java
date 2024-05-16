package kg.attractor.ht49.dto;

import jakarta.validation.constraints.NotNull;
import kg.attractor.ht49.dto.users.UserDto;
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
    private Long respApplId;
    private String senderEmail;
    @NotNull
    private String content;
    private LocalDateTime timestamp;
}
