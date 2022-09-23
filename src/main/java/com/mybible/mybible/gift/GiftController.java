package com.mybible.mybible.gift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/gift")
public class GiftController {

    @Autowired
    private GiftService giftService;

    @PostMapping("/add")
    public Gift add(@RequestBody Gift gift){
        return giftService.saveGift(gift);
    }

    @GetMapping("/getAll")
    public List<Gift> getAllGifts(){
        return giftService.getAllGifts();
    }

    @RequestMapping("/{giftId}")
    public Optional<Gift> getGift(@PathVariable Long giftId){
        return giftService.getGift(giftId);
    }

    @PutMapping("/update/{giftId}")
    public Gift updateGift(@PathVariable Long giftId, @RequestBody Gift gift){
        return giftService.updateGift(giftId, gift);
    }

    @DeleteMapping("/delete/{giftId}")
    public void deleteGift(@PathVariable Long giftId){
        giftService.deleteGift(giftId);
    }
}