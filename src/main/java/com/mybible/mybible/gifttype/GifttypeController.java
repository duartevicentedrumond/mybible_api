package com.mybible.mybible.gifttype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/gifttype")
public class GifttypeController {

    @Autowired
    private GifttypeService gifttypeService;

    @PostMapping("/add")
    public Gifttype add(@RequestBody Gifttype gifttype){
        return gifttypeService.saveGifttype(gifttype);
    }

    @GetMapping("/getAll")
    public List<Gifttype> getAllGifttypes(){
        return gifttypeService.getAllGifttypes();
    }

    @RequestMapping("/{gifttypeId}")
    public Gifttype getGifttype(@PathVariable Long gifttypeId){
        return gifttypeService.getGifttype(gifttypeId);
    }

    @PutMapping("/update/{gifttypeId}")
    public Gifttype updateGifttype(@PathVariable Long gifttypeId, @RequestBody Gifttype gifttype){
        return gifttypeService.updateGifttype(gifttypeId, gifttype);
    }

    @DeleteMapping("/delete/{gifttypeId}")
    public void deleteGifttype(@PathVariable Long gifttypeId){
        gifttypeService.deleteGifttype(gifttypeId);
    }

}