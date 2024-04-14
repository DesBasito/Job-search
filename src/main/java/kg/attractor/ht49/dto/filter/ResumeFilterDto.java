package kg.attractor.ht49.dto.filter;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeFilterDto {
    protected String category;
    protected Double salary;
    protected String email;
    private String uni;
    private String companyWork;
}
