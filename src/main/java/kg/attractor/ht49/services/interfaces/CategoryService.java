package kg.attractor.ht49.services.interfaces;

import kg.attractor.ht49.dto.CategoryDto;
import kg.attractor.ht49.models.Category;

import java.util.List;

public interface CategoryService {
    Category getCategoryByName(String category);
    CategoryDto getCategoryById(Long id);

    List<CategoryDto> getCategories();
}