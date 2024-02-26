package com.finalassignment.assignment.service.impl;

import com.finalassignment.assignment.dto.ItemDto;
import com.finalassignment.assignment.exception.ItemNotFoundByIdException;
import com.finalassignment.assignment.exception.ItemNotFoundException;
import com.finalassignment.assignment.mapper.ItemMapper;
import com.finalassignment.assignment.model.Item;
import com.finalassignment.assignment.repository.ItemRepo;
import com.finalassignment.assignment.service.ItemService;
import com.finalassignment.assignment.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@PropertySource("classpath:messages.properties")
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepo itemRepo;
    private static final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);

    private Optional<Item> findItemId(int itemId) {
        Optional<Item> itemById = itemRepo.findById(itemId);
        if (itemById.isEmpty()) {
            logger.error("ItemNotFoundById: {}", Constant.itemNotFoundById);
            throw new ItemNotFoundByIdException(itemId);
        }
        return itemById;
    }

    @Override
    public List<ItemDto> showAllItem() {
        List<Item> list = itemRepo.findAll();
        if (list.isEmpty()) {
            logger.error("ItemNotFound: {}", Constant.itemNotFound);
            throw new ItemNotFoundException();
        }
        logger.info("ItemFound: {}", Constant.itemFound);
        return list.stream().map(ItemMapper.INSTANCE::toDto).collect(Collectors.toList());

    }

    @Override
    public Optional<Item> showItemById(int itemId) {
        logger.info("ItemFound: {}", Constant.itemFoundById);
        return findItemId(itemId);
    }

    @Override
    public void addItems(ItemDto itemDto) {
        logger.info("ItemAdded: {}", Constant.itemAdded);
        itemRepo.save(ItemMapper.INSTANCE.toModel(itemDto));
    }

    @Override
    public void updateItem(ItemDto itemDto) {
        findItemId(itemDto.getId());
        logger.info("ItemUpdated: {}", Constant.itemUpdated);
        itemRepo.save(ItemMapper.INSTANCE.toModel(itemDto));
    }

    @Override
    public void deleteItems(int itemId) {
        findItemId(itemId);
        logger.info("ItemDeleted: {}", Constant.itemDeleted);
        itemRepo.deleteById(itemId);
    }

}