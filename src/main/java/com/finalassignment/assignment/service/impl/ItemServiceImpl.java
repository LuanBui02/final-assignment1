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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@PropertySource("classpath:message.properties")
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepo itemRepo;
    private final ItemMapper itemMapper = Mappers.getMapper(ItemMapper.class);
    private static final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);
    @Value("${Error01}")
    private String error1;
    @Value("${Error02}")
    private String error2;
    @Value("${Error03}")
    private String error3;
    @Value("${ItemFound}")
    private String itemFound;
    @Value("${ItemUpdated}")
    private String itemUpdate;
    @Value("${Error04}")
    private String error4;
    @Value("${ItemDeleted}")
    private String itemDeleted;
    @Value("${ItemAdded}")
    private String itemAdded;

    @Override
    public List<ItemDto> showAllItem() {
        List<Item> list = itemRepo.findAll();
        if (list.isEmpty()) {
            logger.error(error1);
            throw new ItemNotFoundException();
        }
        logger.info(itemFound);
        return list.stream().map(itemMapper::toDto).collect(Collectors.toList());

    }

    @Override
    public Optional<Item> showItemById(int itemId) {
        Optional<Item> itemById = itemRepo.findById(itemId);
        if (itemById.isEmpty()) {
            logger.error(error2);
            throw new ItemNotFoundByIdException(itemId);
        }
        logger.info(itemFound);
        return itemById;
    }

    @Override
    public void addItems(ItemDto itemDto) {
        logger.info(itemAdded);
        itemRepo.save(itemMapper.toModel(itemDto));
    }

    @Override
    public void updateItem(ItemDto itemDto) {
        Optional<Item> item = itemRepo.findById(itemDto.getId());
        if (item.isEmpty()) {
            logger.error(error3);
            throw new ItemNotFoundByIdException(itemDto.getId());
        }
        logger.info(itemUpdate);
        itemRepo.save(itemMapper.toModel(itemDto));
    }

    @Override
    public void deleteItems(int itemId) {
        Optional<Item> list = itemRepo.findById(itemId);
        if (list.isEmpty()) {
            logger.error(error4);
            throw new ItemNotFoundByIdException(itemId);
        }
        logger.info(itemDeleted);
        itemRepo.deleteById(itemId);
    }

}