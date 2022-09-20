package com.mybible.mybible.building;

import java.util.List;

public interface BuildingService {

    Building saveBuilding(Building building);
    Building getBuilding(Long buildingId);
    Building updateBuilding(Long buildingId, Building building);
    List<Building> getAllBuilding();
    void deleteBuilding(Long buildingId);
}
