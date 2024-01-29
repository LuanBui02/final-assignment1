package com.finalassignment.assignment.controller;

import com.finalassignment.assignment.model.Item;
import com.finalassignment.assignment.repository.ItemRepo;
import com.finalassignment.assignment.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemRepo itemRepo;

    @GetMapping()
    public List<Item> getAllItems() {
        return itemRepo.findAll();
    }

    @GetMapping("/{itemId}")
    public Optional<Item> getItemById(@PathVariable int itemId) {
        return itemRepo.findById(itemId);
    }

    @PostMapping()
    public void addItems(@RequestBody Item item) {
        itemRepo.save(item);
    }

    @PutMapping("/{itemId}")
    public Item updateItem(@PathVariable int itemId, @RequestBody Item itemEntity) {
        return itemRepo.findById(itemId)
                .map(item -> {
                    item.setName(itemEntity.getName());
                    item.setPrice(itemEntity.getPrice());
                    return itemRepo.save(item);
                })
                .orElseGet(() -> {
                    itemEntity.setId(itemId);
                    return itemRepo.save(itemEntity);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable int id) {
        itemRepo.deleteById(id);
    }
}
