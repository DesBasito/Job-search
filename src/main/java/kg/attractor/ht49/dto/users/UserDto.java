package kg.attractor.ht49.dto.users;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name,surname;
    private Integer age;
    private String email;
    private String phoneNumber;
    private String avatar;
    private String accType;
}
