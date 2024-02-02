package com.finalassignment.assignment.service.impl;

import com.finalassignment.assignment.dto.CartDetailDto;
import com.finalassignment.assignment.dto.ItemDto;
import com.finalassignment.assignment.mapper.CartDetailMapper;
import com.finalassignment.assignment.model.Cart;
import com.finalassignment.assignment.model.CartDetail;
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

    private final CartDetailMapper cartDetailMapper = Mappers.getMapper(CartDetailMapper.class);
    @Override
    public List<ItemDto> showAllItem() {
        return itemRepo.findAll().stream().map(item -> {
            ItemDto itemDto = new ItemDto();
            itemDto.setId(item.getId());
            itemDto.setName(item.getName());
            itemDto.setPrice(item.getPrice());
            itemDto.setCartDetailsDto(item.getCartDetails());
            itemDto.setOrderDetailsDto(item.getOrderDetails());
            return itemDto;
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<Item> showItemById(int itemId) {
        return itemRepo.findById(itemId);
    }

    @Override
    public void addItems(ItemDto itemDto) {
        Item item = new Item();
        item.setId(itemDto.getId());
        item.setName(itemDto.getName());
        item.setPrice(itemDto.getPrice());
//        CartDetailDto newCartDetail = (CartDetailDto) itemDto.getCartDetailsDto();
//        CartDetail cartDetail = cartDetailMapper.toModel(newCartDetail);
//        item.setCartDetails(cartDetail);
        itemRepo.save(item);
    }

    @Override
    public void updateItem(ItemDto itemDto) {
        Item item = itemRepo.findById(itemDto.getId()).get();
        item.setPrice(itemDto.getPrice());
        item.setName(itemDto.getName());

        itemRepo.save(item);
    }

    @Override
    public void deleteItems(int itemId) {
        itemRepo.deleteById(itemId);
    }

}
