package com.finalassignment.assignment.mapper;

import com.finalassignment.assignment.dto.OrderDetailDto;
import com.finalassignment.assignment.model.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderDetailMapper {
    OrderDetailMapper INSTANCE = Mappers.getMapper(OrderDetailMapper.class);

    @Mapping(source = "order", target = "orderDto")
    @Mapping(source = "item", target = "itemDto")
    OrderDetailDto toDto(OrderDetail orderDetail);

    @Mapping(source = "orderDto", target = "order")
    @Mapping(source = "itemDto", target = "item")
    OrderDetail toModel(OrderDetailDto orderDetailDto);
}
