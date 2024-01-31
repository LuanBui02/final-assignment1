package com.finalassignment.assignment.controller;

import com.finalassignment.assignment.dto.ItemDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    @GetMapping()
    public void getAllItems() {

    }

    @GetMapping("/{itemId}")
    public void getItemById(@PathVariable int itemId) {

    }

    @PostMapping()
    public void addItems(@RequestBody ItemDto itemDto) {

    }

    @PutMapping()
    public void updateItem(@RequestBody ItemDto itemDto) {

    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable int id) {

    }
}
