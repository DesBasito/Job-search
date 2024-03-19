package kg.attractor.ht49.services;

import kg.attractor.ht49.dto.CategoryDto;
import kg.attractor.ht49.models.Category;

public interface CategoryService {
    Category getCategoryByName(String category);
    CategoryDto getCategoryById(Long id);
}