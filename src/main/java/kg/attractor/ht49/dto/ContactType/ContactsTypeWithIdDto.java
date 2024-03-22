package kg.attractor.ht49.dto.ContactType;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactsTypeWithIdDto {
    @Positive
    private Long id;
    @Pattern(regexp = "^[a-zA-Z]+$")
    private String type;
}
