package com.finalassignment.assignment.service.impl;

import com.finalassignment.assignment.dto.CartDetailDto;
import com.finalassignment.assignment.dto.ItemDto;
import com.finalassignment.assignment.dto.OrderDetailDto;
import com.finalassignment.assignment.exception.ItemNotFoundException;
import com.finalassignment.assignment.mapper.CartDetailMapper;
import com.finalassignment.assignment.mapper.OrderDetailMapper;
import com.finalassignment.assignment.model.CartDetail;
import com.finalassignment.assignment.model.Item;
import com.finalassignment.assignment.model.OrderDetail;
import com.finalassignment.assignment.repository.ItemRepo;
import com.finalassignment.assignment.service.ItemService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepo itemRepo;

    private final CartDetailMapper cartDetailMapper = Mappers.getMapper(CartDetailMapper.class);
    private final OrderDetailMapper orderDetailMapper = Mappers.getMapper(OrderDetailMapper.class);

    @Override
    public List<ItemDto> showAllItem() {
        return itemRepo.findAll().stream().map(item -> {
            ItemDto itemDto = new ItemDto();
            itemDto.setId(item.getId());
            itemDto.setName(item.getName());
            itemDto.setPrice(item.getPrice());


            List<CartDetail> cartDetailDto = item.getCartDetails();
            List<CartDetailDto> cartDetails = cartDetailMapper.toListDto(cartDetailDto);
            itemDto.setCartDetailsDto(cartDetails);

            List<OrderDetail> orderDetails = item.getOrderDetails();
            List<OrderDetailDto> orderDetailDto = orderDetailMapper.toListDto(orderDetails);

            itemDto.setOrderDetailsDto(orderDetailDto);
            return itemDto;
        }).sorted(Comparator.comparing(ItemDto::getId)).collect(Collectors.toList());
    }

    @Override
    public Optional<Item> showItemById(int itemId) {
        if (itemRepo.findById(itemId).isEmpty()) {
            throw new ItemNotFoundException(itemId);
        }
        return itemRepo.findById(itemId);
    }

    @Override
    public void addItems(ItemDto itemDto) {
        Item item = new Item();
        item.setId(itemDto.getId());
        item.setName(itemDto.getName());
        item.setPrice(itemDto.getPrice());

        List<CartDetailDto> cartDetailDto = itemDto.getCartDetailsDto();
        List<CartDetail> cartDetails = cartDetailMapper.toListModel(cartDetailDto);
        item.setCartDetails(cartDetails);

        List<OrderDetailDto> orderDetailDto = itemDto.getOrderDetailsDto();
        List<OrderDetail> orderDetails = orderDetailMapper.toListModel(orderDetailDto);
        item.setOrderDetails(orderDetails);

        itemRepo.save(item);
    }

    @Override
    public void updateItem(ItemDto itemDto) {
        if (itemRepo.findById(itemDto.getId()).isPresent()) {
            Item item = itemRepo.findById(itemDto.getId()).get();
            item.setPrice(itemDto.getPrice());
            item.setName(itemDto.getName());

            itemRepo.save(item);
        } else {
            throw new ItemNotFoundException(itemDto.getId());
        }
    }

    @Override
    public void deleteItems(int itemId) {
        itemRepo.deleteById(itemId);
    }

}
