package com.item.service;

import com.item.model.Item;

import java.util.List;

public interface ItemService {

    List<Item> getAllItems();

    Item selectItem(Long id);

    boolean addItem(Item item);

    boolean updateItem(Item item);
}
