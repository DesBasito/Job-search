package kg.attractor.ht49.dto.ContactInfo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import kg.attractor.ht49.dto.ContactType.ContactTypeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactsInfoDto {

    @Size(max = 145)
    private String type;

    @Size(max = 255)
    private String infoValue;
}
