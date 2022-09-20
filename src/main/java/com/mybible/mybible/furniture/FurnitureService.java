package com.mybible.mybible.furniture;

import java.util.List;

public interface FurnitureService {

    Furniture saveFurniture(Furniture furniture);
    Furniture getFurniture(Long furnitureId);
    Furniture updateFurniture(Long furnitureId, Furniture furniture);
    List<Furniture> getAllFurniture();
    void deleteFurniture(Long furnitureId);
}
