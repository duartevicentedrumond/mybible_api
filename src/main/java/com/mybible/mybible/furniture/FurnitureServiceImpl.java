package com.mybible.mybible.furniture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FurnitureServiceImpl implements FurnitureService {

    @Autowired
    private FurnitureRepository furnitureRepository;

    @Override
    public Furniture saveFurniture(Furniture furniture) {
        return furnitureRepository.save(furniture);
    }

    @Override
    public List<Furniture> getAllFurniture() {
        return furnitureRepository.findAllByOrderByFurnitureId();
    }

    @Override
    public Furniture getFurniture(Long furnitureId) {
        return furnitureRepository.findByFurnitureId(furnitureId);
    }

    @Override
    public Furniture updateFurniture(Long furnitureId, Furniture furniture) {
        return furnitureRepository.save(furniture);
    }

    @Override
    public void deleteFurniture(Long furnitureId) {
        furnitureRepository.deleteById(furnitureId);
    }
}
