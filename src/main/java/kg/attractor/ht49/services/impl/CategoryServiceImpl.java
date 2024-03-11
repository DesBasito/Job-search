package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.dao.CategoryDao;
import kg.attractor.ht49.exceptions.CategoryNotFoundException;
import kg.attractor.ht49.models.Category;
import kg.attractor.ht49.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao dao;
    @Override
    public Long getCategoryId(String category) throws CategoryNotFoundException {
        Category category1 =  dao.getCategoryID(category).orElseThrow(() -> new CategoryNotFoundException("category: " + category+" does not exists"));
        return category1.getId();
    }
}
