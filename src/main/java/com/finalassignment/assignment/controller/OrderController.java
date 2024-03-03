package com.finalassignment.assignment.controller;

import com.finalassignment.assignment.dto.OrderDto;
import com.finalassignment.assignment.service.OrderService;
import com.finalassignment.assignment.util.AbstractMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController extends AbstractMessage {
    @Autowired
    private OrderService orderService;
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @GetMapping("/{customerId}")
    public List<OrderDto> getOrderOfCustomer(@PathVariable int customerId) {
        logger.info("StartToGetAllOrderOfCustomer: {}", getMessage("StartToGetAllOrderOfCustomer") );
        return orderService.showOrderDto(customerId);
    }

    @PostMapping()
    public void createOrder(@RequestBody OrderDto orderDto) {
        logger.info("StartToAddOrderForCustomer: {}", getMessage("StartToAddOrderForCustomer") );
        orderService.addOrder(orderDto);
    }

    @GetMapping("/latestOrder/{customerId}")
    public OrderDto getOrderLatest(@PathVariable int customerId) {
        logger.info("StartToGetTheLatestOrder: {}", getMessage("StartToGetTheLatestOrder") );
        return orderService.showOrderLatest(customerId);
    }
}
