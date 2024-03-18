package kg.attractor.ht49.models;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class User {
    private Long id;
    private String name;
    private String surname;
    private Byte age;
    private String email;
    private String password;
    private String phoneNumber;
    private String avatar;
    private String accType;
}
