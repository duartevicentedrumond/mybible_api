package com.mybible.mybible.category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Category saveCategory(Category category);
    Category getCategory(Long categoryId);
    Category updateCategory(Long categoryId, Category category);
    List<Category> getAllCategory();
    void deleteCategory(Long categoryId);
}
