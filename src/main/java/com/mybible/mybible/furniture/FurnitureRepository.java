package com.mybible.mybible.furniture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FurnitureRepository extends JpaRepository<Furniture, Long> {
    List<Furniture> findAllByOrderByFurnitureId();
    Furniture findByFurnitureId(Long furnitureId);
}
