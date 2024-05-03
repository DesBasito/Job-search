package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dao.CategoryDao;
import kg.attractor.ht49.dto.CategoryDto;
import kg.attractor.ht49.exceptions.CategoryNotFoundException;
import kg.attractor.ht49.models.Category;
import kg.attractor.ht49.repositories.CategoryRepository;
import kg.attractor.ht49.services.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public Category getCategoryByName(String category){
        return categoryRepository.findCategoryByName(category).orElseThrow(() -> new CategoryNotFoundException("Category by name "+category+" not found"));
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category= categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category by id "+id+" not found"));
        return getDto(category);
    }

    @Override
    public List<CategoryDto> getCategories() {
        return categoryRepository.findAll().stream().map(this::getDto).collect(Collectors.toList());
    }

    private CategoryDto getDto(Category category){
        return CategoryDto.builder()
                .name(category.getName())
                .id(category.getId())
                .categoryParent(category.getParentCategory())
                .build();
    }
}
