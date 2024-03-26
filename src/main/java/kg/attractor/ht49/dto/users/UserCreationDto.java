package kg.attractor.ht49.dto.users;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreationDto {

    @NotBlank @Size(max = 30)
    private String name;

    @NotBlank @Size(max = 30)
    private String surname;

    @Min(value = 14, message = "The age must be over 18")
    private Byte age;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).+$", message = "Should contain at least one uppercase letter, one number")
    private String password;

    @Size(max = 25)
    private String phoneNumber;

    private MultipartFile avatar;

    @NotBlank @Pattern(regexp = "^(?=.*[a-z])(?=.*[a-zA-Z]).+$", message = "Should contain only letters")
    private String accType;
}
