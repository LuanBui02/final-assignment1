package com.finalassignment.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCartDto {
    private CustomerDto customerId;
    private int quantity;
    private ItemDto itemId;
}
