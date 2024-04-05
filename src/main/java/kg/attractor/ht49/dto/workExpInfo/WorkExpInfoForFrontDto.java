package kg.attractor.ht49.dto.workExpInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkExpInfoForFrontDto {
    private Long id;
    private Byte years;
    private String companyName;
    private String position;
    private String responsibilities;
}
