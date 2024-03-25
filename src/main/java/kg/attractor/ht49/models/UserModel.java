package kg.attractor.ht49.models;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UserModel {
    private Long id;
    private String name;
    private String surname;
    private Byte age;
    private String email;
    private String password;
    private String phoneNumber;
    private String avatar;
    private Boolean enabled;
}
