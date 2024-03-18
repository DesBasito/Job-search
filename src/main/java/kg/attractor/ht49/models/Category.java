package kg.attractor.ht49.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {
    private Long id;
    private String name;
    private Long parentId;
}
