package com.finalassignment.assignment.dto;

import com.finalassignment.assignment.model.Cart;
import com.finalassignment.assignment.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private int id;
    private String username;
    private String password;
    private String type;
    private Set<Cart> carts;
    private Set<Order> orders;
}
