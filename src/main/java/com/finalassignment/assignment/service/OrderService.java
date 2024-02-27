package com.finalassignment.assignment.service;

import com.finalassignment.assignment.dto.OrderCustomerDto;
import com.finalassignment.assignment.dto.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> showOrderDto(int customerId);

    void addOrder(OrderCustomerDto orderCustomerDto);

    OrderDto showOrderLatest(int customerId);
}
