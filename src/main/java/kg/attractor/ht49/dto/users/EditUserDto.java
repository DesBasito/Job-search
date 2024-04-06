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
public class EditUserDto {
    @Size(max = 30)
    private String name;

    @Size(max = 30)
    private String surname;

    @Min(value = 18, message = "The age must be over 18")
    @Max(value = 70,message = "Enter rational age")
    private Byte age;

    @Size(max = 25)
    private String phoneNumber;
}
