package com.mybible.mybible.category.controller;

import com.mybible.mybible.category.model.Category;
import com.mybible.mybible.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public Category add(@RequestBody Category category){
        return categoryService.saveCategory(category);

    }

    @GetMapping("/getAll")
    public List<Category> getAllCategory(){
        return categoryService.getAllCategory();
    }

    @RequestMapping("/{categoryId}")
    public Optional<Category> getCategory(@PathVariable Long categoryId){
        return categoryService.getCategory(categoryId);
    }

    @PutMapping("/update/{categoryId}")
    public Category updateCategory(@PathVariable Long categoryId, @RequestBody Category category){
        return categoryService.updateCategory(categoryId, category);
    }

}