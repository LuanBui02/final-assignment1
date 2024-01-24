package com.finalassignment.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDto {
    private int id;
    private int orderId;
    private int itemId;
    private int quantity;
}
