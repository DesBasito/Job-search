package kg.attractor.ht49.dto.users;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditUserDto {
    private Long id;
    private String name;
    private String surname;
    private Byte age;
    private String password;
    private String phoneNumber;
    private String avatar;
}