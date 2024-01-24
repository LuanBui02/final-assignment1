package com.finalassignment.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private int id;
    private CustomerDto customerDto;
    private Set<CartDetailDto> cartDetailsDto;
}
