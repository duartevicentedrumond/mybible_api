package com.mybible.mybible.section;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/section")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @PostMapping("/add")
    public Section add(@RequestBody Section section){
        return sectionService.saveSection(section);

    }

    @GetMapping("/getAll")
    public List<Section> getAllSection(){
        return sectionService.getAllSection();
    }

    @RequestMapping("/{sectionId}")
    public Section getSection(@PathVariable Long sectionId){
        return sectionService.getSection(sectionId);
    }

    @PutMapping("/update/{sectionId}")
    public Section updateSection(@PathVariable Long sectionId, @RequestBody Section section){
        return sectionService.updateSection(sectionId, section);
    }

    @DeleteMapping("/delete/{sectionId}")
    public void deleteSection(@PathVariable Long sectionId){
        sectionService.deleteSection(sectionId);
    }

}