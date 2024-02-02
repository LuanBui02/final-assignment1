package com.finalassignment.assignment.service;

import com.finalassignment.assignment.dto.ItemDto;
import com.finalassignment.assignment.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    List<ItemDto> showAllItem();

    Optional<Item> showItemById(int itemId);

    void addItems(ItemDto itemDto);

    void updateItem(ItemDto itemDto);

    void deleteItems(int itemId);
}
