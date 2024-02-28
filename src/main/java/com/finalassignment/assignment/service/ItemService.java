package com.finalassignment.assignment.service;

import com.finalassignment.assignment.dto.ItemDto;

import java.util.List;

public interface ItemService {
    List<ItemDto> showAllItem();

    ItemDto showItemById(int itemId);

    void addItems(ItemDto itemDto);

    void updateItem(ItemDto itemDto);

    void deleteItems(int itemId);
}
