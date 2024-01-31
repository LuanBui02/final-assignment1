package com.finalassignment.assignment.controller;

import com.finalassignment.assignment.dto.CustomerDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @GetMapping("/{customerId}")
    public void getOrderOfCustomer(@PathVariable int customerId) {

    }

    @PostMapping()
    public void createOrder(@RequestBody CustomerDto customerDto) {

    }

    @GetMapping("/latestOrder/{customerId}")
    public void getOrderLatest(@PathVariable int customerId) {

    }
}
