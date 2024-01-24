package com.finalassignment.assignment.mapper;

import com.finalassignment.assignment.dto.CartDetailDto;
import com.finalassignment.assignment.model.CartDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartDetailMapper {
    CartDetailMapper INSTANCE = Mappers.getMapper(CartDetailMapper.class);
    @Mapping(source = "cart", target = "cartDto")
    @Mapping(source = "item", target = "itemDto")
    CartDetailDto toDto(CartDetail cartDetail);
    @Mapping(source = "cartDto", target = "cart")
    @Mapping(source = "itemDto", target = "item")
    CartDetail toModel(CartDetailDto cartDetailDto);
}
