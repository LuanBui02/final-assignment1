package com.finalassignment.assignment.controller;

import com.finalassignment.assignment.dto.CustomerDto;
import com.finalassignment.assignment.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
    @Autowired
    private CustomerService customerService;
    @PostMapping
    public void addAccount(@RequestBody CustomerDto customerDto) {
        customerService.addUser(customerDto);
    }
    @GetMapping
    public List<CustomerDto> getAll() {
        return customerService.getAllCustomer();
    }
    @GetMapping("/user")
    public CustomerDto getUser(@RequestParam String username,
                           @RequestParam String password) {
        return customerService.getCustomer(username, password);
    }
}
