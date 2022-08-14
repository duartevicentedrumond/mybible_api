package com.mybible.mybible.category;

import com.mybible.mybible.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByOrderByCategoryId();
    Category findByCategoryId(Long categoryId);
}
