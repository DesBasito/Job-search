package kg.attractor.ht49.models;

import lombok.Data;

@Data
public class Categories {
    private Long id;
    private String name;
    private Long parentId;
}
