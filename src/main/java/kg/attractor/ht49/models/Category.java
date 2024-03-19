package kg.attractor.ht49.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Category {
    private Long id;
    private String name;
    private Long parentId;
}
