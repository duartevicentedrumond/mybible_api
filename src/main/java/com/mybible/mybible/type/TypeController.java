package com.mybible.mybible.type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @PostMapping("/add")
    public Type add(@RequestBody Type type){
        return typeService.saveType(type);

    }

    @GetMapping("/getAll")
    public List<Type> getAllTypes(){
        return typeService.getAllTypes();
    }

    @RequestMapping("/{typeId}")
    public Type getType(@PathVariable Long typeId){
        return typeService.getType(typeId);
    }

    @PutMapping("/update/{typeId}")
    public Type updateType(@PathVariable Long typeId, @RequestBody Type type){
        return typeService.updateType(typeId, type);
    }

    @DeleteMapping("/delete/{typeId}")
    public void deleteType(@PathVariable Long typeId){
        typeService.deleteType(typeId);
    }

}