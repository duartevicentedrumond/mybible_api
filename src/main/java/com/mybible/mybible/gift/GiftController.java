package com.mybible.mybible.gift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
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

        System.out.println(gift);

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

    @GetMapping("/getGiftByPerson")
    public List<GiftByPerson> getGiftByPerson(){

        List<GiftByPerson> listgiftByPerson = new ArrayList<>();
        List<Object[]> giftByPerson = giftService.getGiftByPerson();

        giftByPerson.forEach(gift ->{

            GiftByPerson new_gift = new GiftByPerson();
            new_gift.setGiftId(((BigInteger) gift[0]).longValue());
            new_gift.setDate((Date) gift[1]);
            new_gift.setDescription((String) gift[2]);
            new_gift.setFrom((Boolean) gift[3]);
            new_gift.setValue((Double) gift[4]);
            new_gift.setNickname((String) gift[5]);
            new_gift.setGifttypeDescription((String) gift[6]);
            new_gift.setPersonId(((BigInteger) gift[7]).longValue());
            listgiftByPerson.add(new_gift);
        });

        return listgiftByPerson;

    }
}