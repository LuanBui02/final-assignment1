package com.finalassignment.assignment.service;

import com.finalassignment.assignment.dto.OrderDto;

public interface OrderService {
    OrderDto showOrder(int customerId);

    void addOrder(OrderDto orderDto);

    OrderDto showOrderLatest(int customerId);
}
