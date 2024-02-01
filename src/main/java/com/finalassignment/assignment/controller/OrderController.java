package com.finalassignment.assignment.controller;

import com.finalassignment.assignment.dto.OrderDto;
import com.finalassignment.assignment.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/{customerId}")
    public OrderDto getOrderOfCustomer(@PathVariable int customerId) {
        return orderService.showOrder(customerId);
    }

    @PostMapping()
    public void createOrder(@RequestBody OrderDto orderDto) {
        orderService.addOrder(orderDto);
    }

    @GetMapping("/latestOrder/{customerId}")
    public OrderDto getOrderLatest(@PathVariable int customerId) {
        return orderService.showOrderLatest(customerId);
    }
}
