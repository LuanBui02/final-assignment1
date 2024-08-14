package com.finalassignment.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartItemDto {
    private Integer id;
    private Integer customerId;
    private Integer itemId;
    private Integer quantity;
}
