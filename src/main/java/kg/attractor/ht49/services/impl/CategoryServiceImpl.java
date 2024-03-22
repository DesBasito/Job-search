package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dao.CategoryDao;
import kg.attractor.ht49.dto.CategoryDto;
import kg.attractor.ht49.exceptions.CategoryNotFoundException;
import kg.attractor.ht49.models.Category;
import kg.attractor.ht49.services.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao dao;
    @Override
    public Category getCategoryByName(String category){
        return dao.getCategoryByName(category).orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category= dao.getCategoryById(id).orElseThrow(CategoryNotFoundException::new);

        return CategoryDto.builder()
                .name(category.getName())
                .id(category.getId())
                .category(dao.getCategoryById(category.getParentId()).orElse(null))
                .build();
    }
}
