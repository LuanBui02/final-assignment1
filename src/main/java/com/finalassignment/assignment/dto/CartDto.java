package com.finalassignment.assignment.dto;

import com.finalassignment.assignment.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private int id;
    private Customer customer;
    private Set<CartDetailDto> cartDetails;
}
