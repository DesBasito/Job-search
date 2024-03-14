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
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao dao;
    @Override
    public Category getCategoryIdByName(String category){
        try {
        return dao.getCategoryByName(category).orElseThrow(CategoryNotFoundException::new);
        }catch (CategoryNotFoundException e){
            log.error("category: {} does not exists",category);
        }
        return null;
    }

    @Override
    public CategoryDto getCategoryById(Long id)  {
        try {
        Category category= dao.getCategoryById(id)
                .orElseThrow(
                        CategoryNotFoundException::new);

        return CategoryDto.builder()
                .name(category.getName())
                .id(category.getId())
                .category(dao.getCategoryById(category.getParentId()).orElse(null))
                .build();
        }catch (CategoryNotFoundException e){
            log.error("category with id:{} not found",id);
        }
        return null;
    }
}
