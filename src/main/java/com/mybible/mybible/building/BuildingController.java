package com.mybible.mybible.building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/building")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @PostMapping("/add")
    public Building add(@RequestBody Building building){
        return buildingService.saveBuilding(building);

    }

    @GetMapping("/getAll")
    public List<Building> getAllBuilding(){
        return buildingService.getAllBuilding();
    }

    @RequestMapping("/{buildingId}")
    public Building getBuilding(@PathVariable Long buildingId){
        return buildingService.getBuilding(buildingId);
    }

    @PutMapping("/update/{buildingId}")
    public Building updateBuilding(@PathVariable Long buildingId, @RequestBody Building building){
        return buildingService.updateBuilding(buildingId, building);
    }

    @DeleteMapping("/delete/{buildingId}")
    public void deleteBuilding(@PathVariable Long buildingId){
        buildingService.deleteBuilding(buildingId);
    }

}