package kg.attractor.ht49.dto.users;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEditOnFrontDto {
    @Min(value = 1)
    private Long id;

    @Size(max = 30)
    private String name;

    @Size(max = 30)
    private String surname;

    @Min(value = 18, message = "The age must be over 18")
    @Max(value = 70,message = "Enter rational age")
    private Byte age;

    @NotBlank
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).+$", message = "Should contain at least one uppercase letter, one number")
    private String password;

    @Size(max = 25)
    private String phoneNumber;
}
