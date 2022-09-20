package com.mybible.mybible.item;

import java.util.List;

public interface ItemService {

    Item saveItem(Item item);
    Item getItem(Long itemId);
    Item updateItem(Long itemId, Item item);
    List<Item> getAllItem();
    void deleteItem(Long itemId);
}
