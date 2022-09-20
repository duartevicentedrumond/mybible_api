package com.mybible.mybible.furniture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/furniture")
public class FurnitureController {

    @Autowired
    private FurnitureService furnitureService;

    @PostMapping("/add")
    public Furniture add(@RequestBody Furniture furniture){
        return furnitureService.saveFurniture(furniture);

    }

    @GetMapping("/getAll")
    public List<Furniture> getAllFurniture(){
        return furnitureService.getAllFurniture();
    }

    @RequestMapping("/{furnitureId}")
    public Furniture getFurniture(@PathVariable Long furnitureId){
        return furnitureService.getFurniture(furnitureId);
    }

    @PutMapping("/update/{furnitureId}")
    public Furniture updateFurniture(@PathVariable Long furnitureId, @RequestBody Furniture furniture){
        return furnitureService.updateFurniture(furnitureId, furniture);
    }

    @DeleteMapping("/delete/{furnitureId}")
    public void deleteFurniture(@PathVariable Long furnitureId){
        furnitureService.deleteFurniture(furnitureId);
    }

}