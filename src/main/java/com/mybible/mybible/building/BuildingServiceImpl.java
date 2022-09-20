package com.mybible.mybible.building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Override
    public Building saveBuilding(Building building) {
        return buildingRepository.save(building);
    }

    @Override
    public List<Building> getAllBuilding() {
        return buildingRepository.findAllByOrderByBuildingId();
    }

    @Override
    public Building getBuilding(Long buildingId) {
        return buildingRepository.findByBuildingId(buildingId);
    }

    @Override
    public Building updateBuilding(Long buildingId, Building building) {
        return buildingRepository.save(building);
    }

    @Override
    public void deleteBuilding(Long buildingId) {
        buildingRepository.deleteById(buildingId);
    }
}
