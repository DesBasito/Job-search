package kg.attractor.ht49.dto.users;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditUserDto {
    @Min(value = 1)
    private Long id;
    @Size(max = 30)
    private String name,surname;
    @Min(value = 18, message = "The age must be over 18")
    @Max(value = 70,message = "Enter rational age")
    private Byte age;
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).+$", message = "Should contain at least one uppercase letter, one number")
    private String password;
    @Size(max = 20) @Pattern(regexp ="^\\d+$",message = "enter phone number(only digits)")
    private String phoneNumber;
    private MultipartFile avatar;
}
