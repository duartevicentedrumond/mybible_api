package com.mybible.mybible.building;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
    List<Building> findAllByOrderByBuildingId();
    Building findByBuildingId(Long buildingId);
}
