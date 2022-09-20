package com.mybible.mybible.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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