package com.finalassignment.assignment.dto;

import com.finalassignment.assignment.model.CartDetail;
import com.finalassignment.assignment.model.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private int id;
    private String name;
    private int price;
    private Set<CartDetail> cartDetails;
    private Set<OrderDetail> orderDetails;
}
