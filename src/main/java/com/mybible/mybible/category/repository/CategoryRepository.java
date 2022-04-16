package com.mybible.mybible.category.repository;

import com.mybible.mybible.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByOrderByCategoryId();
    Optional<Category> findByCategoryId(Long categoryId);
}
