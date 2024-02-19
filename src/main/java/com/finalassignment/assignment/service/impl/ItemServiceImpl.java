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
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@PropertySource("classpath:messages.properties")
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepo itemRepo;
    private final ItemMapper itemMapper = Mappers.getMapper(ItemMapper.class);
    private static final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);
    @Autowired
    private MessageSource messageSource;


    @Override
    public List<ItemDto> showAllItem() {
        List<Item> list = itemRepo.findAll();
        if (list.isEmpty()) {
            String itemNotFound = messageSource.getMessage("ItemNotFound", null, Locale.ENGLISH);
            logger.error("ItemNotFound: {}", itemNotFound);
            throw new ItemNotFoundException();
        }
        String itemFound = messageSource.getMessage("ItemFound", null, Locale.ENGLISH);
        logger.info("ItemFound: {}", itemFound);
        return list.stream().map(itemMapper::toDto).collect(Collectors.toList());

    }

    @Override
    public Optional<Item> showItemById(int itemId) {
        Optional<Item> itemById = itemRepo.findById(itemId);
        if (itemById.isEmpty()) {
            String itemNotFoundById = messageSource.getMessage("NotFoundItemById", null, Locale.ENGLISH);
            logger.error("ItemNotFoundById: {}", itemNotFoundById);
            throw new ItemNotFoundByIdException(itemId);
        }
        String itemFound = messageSource.getMessage("ItemFound", null, Locale.ENGLISH);
        logger.info("ItemFound: {}", itemFound);
        return itemById;
    }

    @Override
    public void addItems(ItemDto itemDto) {
        String itemAdded = messageSource.getMessage("ItemAdded", null, Locale.ENGLISH);
        logger.info("ItemAdded: {}", itemAdded);
        itemRepo.save(itemMapper.toModel(itemDto));
    }

    @Override
    public void updateItem(ItemDto itemDto) {
        Optional<Item> item = itemRepo.findById(itemDto.getId());
        String itemNotFound = messageSource.getMessage("ItemNotFound", null, Locale.ENGLISH);
        if (item.isEmpty()) {
            logger.error("NotFoundItemToUpdate: {}", itemNotFound);
            throw new ItemNotFoundByIdException(itemDto.getId());
        }
        String itemUpdate = messageSource.getMessage("ItemUpdated", null, Locale.ENGLISH);
        logger.info("ItemUpdated: {}", itemUpdate);
        itemRepo.save(itemMapper.toModel(itemDto));
    }

    @Override
    public void deleteItems(int itemId) {
        Optional<Item> list = itemRepo.findById(itemId);
        if (list.isEmpty()) {
            String itemNotFound = messageSource.getMessage("ItemNotFound", null, Locale.ENGLISH);
            logger.error("NotFoundItemToDelete: {}", itemNotFound);
            throw new ItemNotFoundByIdException(itemId);
        }
        String itemDeleted = messageSource.getMessage("ItemDeleted", null, Locale.ENGLISH);
        logger.info("ItemDeleted: {}", itemDeleted);
        itemRepo.deleteById(itemId);
    }

}