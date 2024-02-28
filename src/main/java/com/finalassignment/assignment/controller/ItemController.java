package com.finalassignment.assignment.controller;

import com.finalassignment.assignment.dto.ItemDto;
import com.finalassignment.assignment.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping()
    public List<ItemDto> getAllItems() {
        return itemService.showAllItem();
    }

    @GetMapping("/{itemId}")
    public ItemDto getItemById(@PathVariable int itemId) {
        return itemService.showItemById(itemId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewItems(@RequestBody ItemDto itemDto) {
        itemService.addItems(itemDto);
    }

    @PutMapping()
    public void updateItem(@RequestBody ItemDto itemDto) {
        itemService.updateItem(itemDto);
    }

    @DeleteMapping("/{itemId}")
    public void deleteItem(@PathVariable int itemId) {
        itemService.deleteItems(itemId);
    }
}
