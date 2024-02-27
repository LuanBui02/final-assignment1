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
public class ItemServiceImpl extends Constant implements ItemService {
    @Autowired
    private ItemRepo itemRepo;
    private static final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);

    private Optional<Item> findItemId(int itemId) {
        Optional<Item> itemById = itemRepo.findById(itemId);
        if (itemById.isPresent()) {
            return itemById;
        } else {
            logger.error("ItemNotFoundById: {}", getItemNotFoundById("NotFoundItemById"));
            throw new ItemNotFoundByIdException(itemId);
        }
    }

    @Override
    public List<ItemDto> showAllItem() {
        List<Item> list = itemRepo.findAll();
        if (list.isEmpty()) {
            logger.error("ItemNotFound: {}", getItemNotFound("ItemNotFound"));
            throw new ItemNotFoundException();
        }
        logger.info("ItemFound: {}", getItemFound("ItemFound"));
        return list.stream().map(ItemMapper.INSTANCE::toDto).collect(Collectors.toList());

    }

    @Override
    public Optional<Item> showItemById(int itemId) {
        if (findItemId(itemId).isPresent()) {
            logger.info("ItemFound: {}", getItemFound("ItemFound"));
            return findItemId(itemId);
        }
        return Optional.empty();
    }

    @Override
    public void addItems(ItemDto itemDto) {
        logger.info("ItemAdded: {}", getItemAdded("ItemAdded"));
        itemRepo.save(ItemMapper.INSTANCE.toModel(itemDto));
    }

    @Override
    public void updateItem(ItemDto itemDto) {
        findItemId(itemDto.getId());
        logger.info("ItemUpdated: {}", getItemUpdated("ItemUpdated"));
        itemRepo.save(ItemMapper.INSTANCE.toModel(itemDto));
    }

    @Override
    public void deleteItems(int itemId) {
        findItemId(itemId);
        logger.info("ItemDeleted: {}", getItemDeleted("ItemDeleted"));
        itemRepo.deleteById(itemId);
    }

}