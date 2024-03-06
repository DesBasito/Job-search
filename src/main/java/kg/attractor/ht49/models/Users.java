package kg.attractor.ht49.models;

import lombok.Data;

@Data
public class Users {
    private Long id;
    private String name;
    private String surname;
    private Byte age;
    private String password;
    private String phoneNumber;
    private String avtar;
    private String accType;
}
