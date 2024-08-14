package com.finalassignment.assignment.service;

import com.finalassignment.assignment.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    void addUser(CustomerDto customerDto);

    List<CustomerDto> getAllCustomer();

    CustomerDto getCustomer(String username, String password);
}
