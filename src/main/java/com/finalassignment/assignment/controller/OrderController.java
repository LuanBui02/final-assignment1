package com.finalassignment.assignment.controller;

import com.finalassignment.assignment.dto.OrderCustomerDto;
import com.finalassignment.assignment.dto.OrderDto;
import com.finalassignment.assignment.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/{customerId}")
    public List<OrderDto> getOrderOfCustomer(@PathVariable int customerId) {
        return orderService.showOrderDto(customerId);
    }

    @PostMapping()
    public void createOrder(@RequestBody OrderCustomerDto orderCustomerDto) {
        orderService.addOrder(orderCustomerDto);
    }

    @GetMapping("/latestOrder/{customerId}")
    public OrderDto getOrderLatest(@PathVariable int customerId) {
        return orderService.showOrderLatest(customerId);
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable int orderId) {
        orderService.deleteOrder(orderId);
    }
}
