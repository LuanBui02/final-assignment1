package com.finalassignment.assignment.controller;

import com.finalassignment.assignment.dto.ItemDto;
import com.finalassignment.assignment.service.ItemService;
import com.finalassignment.assignment.util.AbstractMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/items")
public class ItemController extends AbstractMessage {
    @Autowired
    private ItemService itemService;
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
    @GetMapping()
    public List<ItemDto> getAllItems() {
        logger.info("StartToGetAllItems: {}", getMessage("StartToGetAllItems"));
        return itemService.showAllItem();
    }

    @GetMapping("/{itemId}")
    public ItemDto getItemById(@PathVariable int itemId) {
        logger.info("StartToGetItemById: {}", getMessage("StartToGetItemById"));
        return itemService.showItemById(itemId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewItems(@RequestBody ItemDto itemDto) {
        logger.info("StartToAddItem: {}", getMessage("StartToAddItem"));
        itemService.addItems(itemDto);
    }

    @PutMapping()
    public void updateItem(@RequestBody ItemDto itemDto) {
        logger.info("StartToUpdateItem: {}", getMessage("StartToUpdateItem"));
        itemService.updateItem(itemDto);
    }

    @DeleteMapping("/{itemId}")
    public void deleteItem(@PathVariable int itemId) {
        logger.info("StartToDeleteItem: {}", getMessage("StartToDeleteItem"));
        itemService.deleteItems(itemId);
    }
}
