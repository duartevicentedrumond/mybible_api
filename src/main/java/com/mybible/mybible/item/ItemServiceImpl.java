package com.mybible.mybible.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public List<Item> getAllItem() {
        return itemRepository.findAllByOrderByItemId();
    }

    @Override
    public Item getItem(Long itemId) {
        return itemRepository.findByItemId(itemId);
    }

    @Override
    public Item updateItem(Long itemId, Item item) {
        return itemRepository.save(item);
    }

    @Override
    public void deleteItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }
}
