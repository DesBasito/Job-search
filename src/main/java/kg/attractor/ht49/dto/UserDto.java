package kg.attractor.ht49.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private Byte age;
    private String email;
    private String password;
    private String phoneNumber;
    private String avtar;
    private String accType;
}
