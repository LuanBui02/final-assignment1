package com.finalassignment.assignment.service.impl;

import com.finalassignment.assignment.dto.ItemDto;
import com.finalassignment.assignment.exception.ItemNotFoundException;
import com.finalassignment.assignment.mapper.ItemMapper;
import com.finalassignment.assignment.model.Item;
import com.finalassignment.assignment.repository.ItemRepo;
import com.finalassignment.assignment.service.ItemService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepo itemRepo;
    private final ItemMapper itemMapper = Mappers.getMapper(ItemMapper.class);

    @Override
    public List<ItemDto> showAllItem() {
        List<Item> list = itemRepo.findAll();
        return list.stream().map(itemMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<Item> showItemById(int itemId) {
        Optional<Item> itemById = itemRepo.findById(itemId);

        if (itemById.isEmpty()) {
            throw new ItemNotFoundException(itemId);
        }
        return itemById;
    }

    @Override
    public void addItems(ItemDto itemDto) {
        itemRepo.save(itemMapper.toModel(itemDto));
    }

    @Override
    public void updateItem(ItemDto itemDto) {
        Optional<Item> item = itemRepo.findById(itemDto.getId());
        if (item.isEmpty()) {
            throw new ItemNotFoundException(itemDto.getId());
        }
        itemRepo.save(itemMapper.toModel(itemDto));
    }

    @Override
    public void deleteItems(int itemId) {
        itemRepo.deleteById(itemId);
    }

}