package com.finalassignment.assignment.mapper;

import com.finalassignment.assignment.dto.OrderDetailDto;
import com.finalassignment.assignment.dto.OrderDto;
import com.finalassignment.assignment.model.Order;
import com.finalassignment.assignment.model.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "orderDetails", target = "orderDetailsDto")
    OrderDto toDto(Order order);

    List<OrderDto> toListDto(List<Order> order);

    default OrderDetailDto orderDetailToOrderDetailDto(OrderDetail orderDetail) {
        return OrderDetailMapper.INSTANCE.toDto(orderDetail);
    }

    @Mapping(source = "orderDetailsDto", target = "orderDetails")
    Order toModel(OrderDto orderDto);

    List<Order> toListModel(List<OrderDto> orderDto);
}
