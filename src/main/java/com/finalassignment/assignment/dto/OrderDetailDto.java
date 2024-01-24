package com.finalassignment.assignment.dto;

import com.finalassignment.assignment.model.Item;
import com.finalassignment.assignment.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDto {
    private int id;
    private Order order;
    private Item item;
    private int quantity;
}
