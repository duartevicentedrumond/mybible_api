package com.mybible.mybible.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/add")
    public Item add(@RequestBody Item item){
        return itemService.saveItem(item);

    }

    @GetMapping("/getAll")
    public List<Item> getAllItem(){
        return itemService.getAllItem();
    }

    @GetMapping("/getAllJoined")
    public List<ItemsToBuildings> getAllJoined(){

        List<ItemsToBuildings> listAllJoined = new ArrayList<>();
        List<Object[]> allJoined = itemService.getAllJoined();

        allJoined.forEach(element ->{

            ItemsToBuildings new_element = new ItemsToBuildings();

            new_element.setId(((BigInteger) element[0]).longValue());
            new_element.setName((String) element[1]);
            new_element.setActive((Boolean) element[2]);
            new_element.setSince((Date) element[3]);
            new_element.setUntil((Date) element[4]);
            new_element.setType((String) element[5]);
            listAllJoined.add(new_element);

        });

        return listAllJoined;
    }

    @RequestMapping("/{itemId}")
    public Item getItem(@PathVariable Long itemId){
        return itemService.getItem(itemId);
    }

    @PutMapping("/update/{itemId}")
    public Item updateItem(@PathVariable Long itemId, @RequestBody Item item){
        return itemService.updateItem(itemId, item);
    }

    @DeleteMapping("/delete/{itemId}")
    public void deleteItem(@PathVariable Long itemId){
        itemService.deleteItem(itemId);
    }

}