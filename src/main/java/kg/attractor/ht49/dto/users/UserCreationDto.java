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

    @Min(value = 14, message = "{register.valid.age}")
    private Integer age;

    @NotBlank @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).+$", message = "{register.valid.password}")
    private String password;

    @NotBlank @Size(max = 25)
    private String phoneNumber;

    @NotNull
    private Long accType;
}
