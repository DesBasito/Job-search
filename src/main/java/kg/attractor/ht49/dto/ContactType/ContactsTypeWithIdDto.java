package kg.attractor.ht49.dto.ContactType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactsTypeWithIdDto {
    private Long id;
    private String type;
}
