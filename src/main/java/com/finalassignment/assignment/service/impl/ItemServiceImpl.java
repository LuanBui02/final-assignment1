package com.finalassignment.assignment.service.impl;

import com.finalassignment.assignment.dto.ItemDto;
import com.finalassignment.assignment.exception.ItemNotFoundByIdException;
import com.finalassignment.assignment.exception.ItemNotFoundException;
import com.finalassignment.assignment.mapper.ItemMapper;
import com.finalassignment.assignment.model.Item;
import com.finalassignment.assignment.repository.ItemRepo;
import com.finalassignment.assignment.service.ItemService;
import com.finalassignment.assignment.util.AbstractMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@PropertySource("classpath:messages.properties")
public class ItemServiceImpl extends AbstractMessage implements ItemService {
    @Autowired
    private ItemRepo itemRepo;
    private static final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);

    private void findItemId(int itemId) {
        Optional<Item> itemById = itemRepo.findById(itemId);
        if (itemById.isEmpty()) {
            logger.error("ItemNotFoundById: {}", getMessage("NotFoundItemById"));
            throw new ItemNotFoundByIdException(itemId);

        }
    }

    @Override
    public List<ItemDto> showAllItem() {
        List<Item> list = itemRepo.findAll();
        if (list.isEmpty()) {
            logger.error("ItemNotFound: {}", getMessage("ItemNotFound"));
            throw new ItemNotFoundException();
        }
        List<ItemDto> itemDtoList = ItemMapper.INSTANCE.toListDto(list);
        logger.info("ItemFound: {}", getMessage("ItemFound"));
        return itemDtoList;
    }

    @Override
    public ItemDto showItemById(int itemId) {
        findItemId(itemId);
        ItemDto itemDto = ItemMapper.INSTANCE.toDto(itemRepo.findById(itemId).get());
        logger.info("ItemFound: {}", getMessage("ItemFound"));
        return itemDto;
    }

    @Override
    public void addItems(ItemDto itemDto) {
        itemRepo.save(ItemMapper.INSTANCE.toModel(itemDto));
        logger.info("ItemAdded: {}", getMessage("ItemAdded"));
    }

    @Override
    public void updateItem(ItemDto itemDto) {
        findItemId(itemDto.getId());
        itemRepo.save(ItemMapper.INSTANCE.toModel(itemDto));
        logger.info("ItemUpdated: {}", getMessage("ItemUpdated"));
    }

    @Override
    public void deleteItems(int itemId) {
        findItemId(itemId);
        itemRepo.deleteById(itemId);
        logger.info("ItemDeleted: {}", getMessage("ItemDeleted"));
    }

}