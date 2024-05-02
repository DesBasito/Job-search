package kg.attractor.ht49.dto;

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
    private RespondedApplicantDto respApplId;
    private UserDto senderEmail;
    private String content;
    private LocalDateTime timestamp;
}
