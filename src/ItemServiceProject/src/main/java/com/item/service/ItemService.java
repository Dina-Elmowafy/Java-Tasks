package com.item.service;

import java.util.List;

import com.item.model.Item;

public interface itemService {

    List<Item> getAllItem();

    Item selectItem();

    boolean updatItem(Long id);

    boolean deleteItem(Long id);

    boolean addIteam(Long id);
}