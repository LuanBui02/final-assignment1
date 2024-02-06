package com.finalassignment.assignment.service.impl;

import com.finalassignment.assignment.dto.ItemDto;
import com.finalassignment.assignment.exception.ItemNotFoundByIdException;
import com.finalassignment.assignment.exception.ItemNotFoundException;
import com.finalassignment.assignment.mapper.ItemMapper;
import com.finalassignment.assignment.model.Item;
import com.finalassignment.assignment.repository.ItemRepo;
import com.finalassignment.assignment.service.ItemService;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);
    @Override
    public List<ItemDto> showAllItem() {
        List<Item> list = itemRepo.findAll();
        if(list.isEmpty()) {
            logger.error("Item Not found");
            throw new ItemNotFoundException();
        }
        logger.info("All items found");
        return list.stream().map(itemMapper::toDto).sorted().collect(Collectors.toList());
    }

    @Override
    public Optional<Item> showItemById(int itemId) {
        Optional<Item> itemById = itemRepo.findById(itemId);
        if (itemById.isEmpty()) {
            logger.error("Not found item by id");
            throw new ItemNotFoundByIdException(itemId);
        }
        logger.info("Item found");
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
            logger.error("Not found item to update");
            throw new ItemNotFoundByIdException(itemDto.getId());
        }
        logger.info("Item updated");
        itemRepo.save(itemMapper.toModel(itemDto));
    }

    @Override
    public void deleteItems(int itemId) {
        Optional<Item> list = itemRepo.findById(itemId);
        if(list.isEmpty()) {
            logger.error("Not found item to delete");
            throw new ItemNotFoundByIdException(itemId);
        }
        logger.info("Item deleted");
        itemRepo.deleteById(itemId);
    }

}