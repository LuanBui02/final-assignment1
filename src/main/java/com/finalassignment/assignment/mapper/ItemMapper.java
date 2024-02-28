package com.finalassignment.assignment.mapper;

import com.finalassignment.assignment.dto.ItemDto;
import com.finalassignment.assignment.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    @Mapping(source = "cartDetails", target = "cartDetailsDto")
    @Mapping(source = "orderDetails", target = "orderDetailsDto")
    ItemDto toDto(Item item);

    List<ItemDto> toListDto(List<Item> itemList);

    @Mapping(source = "cartDetailsDto", target = "cartDetails")
    @Mapping(source = "orderDetailsDto", target = "orderDetails")
    Item toModel(ItemDto itemDto);
}
