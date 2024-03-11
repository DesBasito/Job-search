package kg.attractor.ht49.services;

import kg.attractor.ht49.exceptions.CategoryNotFoundException;

public interface CategoryService {
    Long getCategoryId(String category) throws CategoryNotFoundException;
}