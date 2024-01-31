package com.finalassignment.assignment.controller;

import com.finalassignment.assignment.model.Customer;
import com.finalassignment.assignment.model.Order;
import com.finalassignment.assignment.repository.CustomerRepo;
import com.finalassignment.assignment.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @GetMapping("/{customerId}")
    public Optional<Order> getOrderOfCustomer(@PathVariable int customerId) {
        return orderRepo.findById(customerId);
    }

    @PostMapping()
    public void createOrder(@RequestBody Customer customer) {

    }

    @GetMapping("/latestOrder/{customerId}")
    public Optional<Order> getOrderLatest(@PathVariable int customerId) {
        return orderRepo.findById(customerId);
    }
}
