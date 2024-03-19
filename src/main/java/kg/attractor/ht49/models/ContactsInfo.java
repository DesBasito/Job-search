package kg.attractor.ht49.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ContactsInfo {
    private Long id;
    private Long typeId;
    private Long resumeId;
    private String infoValue;
}
