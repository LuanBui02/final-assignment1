package com.finalassignment.assignment.mapper;

import com.finalassignment.assignment.dto.CartDetailDto;
import com.finalassignment.assignment.model.CartDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CartDetailMapper {
    CartDetailMapper INSTANCE = Mappers.getMapper(CartDetailMapper.class);

    @Mapping(source = "cart", target = "cartDto")
    @Mapping(source = "item", target = "itemDto")
    CartDetailDto toDto(CartDetail cartDetail);
    List<CartDetailDto> toListDto(List<CartDetail> cartDetails);
    @Mapping(source = "cartDto", target = "cart")
    @Mapping(source = "itemDto", target = "item")
    CartDetail toModel(CartDetailDto cartDetailDto);
    List<CartDetail> toListModel(List<CartDetailDto> cartDetailsDto);
}
