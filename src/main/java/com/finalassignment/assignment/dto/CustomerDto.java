package com.finalassignment.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private int id;
    private String username;
    private String password;
    private String type;
    private List<CartDto> cartsDto;
    private List<OrderDto> ordersDto;
}
