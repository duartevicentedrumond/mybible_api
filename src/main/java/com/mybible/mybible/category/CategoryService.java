package com.mybible.mybible.category;

import com.mybible.mybible.category.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    public Category saveCategory(Category category);
    public Optional<Category> getCategory(Long categoryId);
    public Category updateCategory(Long categoryId, Category category);
    List<Category> getAllCategory();
}