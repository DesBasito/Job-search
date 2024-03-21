package kg.attractor.ht49.dto.ContactInfo;

import kg.attractor.ht49.dto.ContactType.ContactTypeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactsInfoWithIdDto {
    private Long id;
    private String type;
    private String infoValue;
}
