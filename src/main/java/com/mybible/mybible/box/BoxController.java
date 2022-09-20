package com.mybible.mybible.box;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/box")
public class BoxController {

    @Autowired
    private BoxService boxService;

    @PostMapping("/add")
    public Box add(@RequestBody Box box){
        return boxService.saveBox(box);

    }

    @GetMapping("/getAll")
    public List<Box> getAllBox(){
        return boxService.getAllBox();
    }

    @RequestMapping("/{boxId}")
    public Box getBox(@PathVariable Long boxId){
        return boxService.getBox(boxId);
    }

    @PutMapping("/update/{boxId}")
    public Box updateBox(@PathVariable Long boxId, @RequestBody Box box){
        return boxService.updateBox(boxId, box);
    }

    @DeleteMapping("/delete/{boxId}")
    public void deleteBox(@PathVariable Long boxId){
        boxService.deleteBox(boxId);
    }

}