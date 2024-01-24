package com.finalassignment.assignment.mapper;

import com.finalassignment.assignment.dto.OrderDto;
import com.finalassignment.assignment.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "orderDetails", target = "orderDetailsDto")
    OrderDto toDto(Order order);

    @Mapping(source = "orderDetailsDto", target = "orderDetails")
    Order toModel(OrderDto orderDto);
}
