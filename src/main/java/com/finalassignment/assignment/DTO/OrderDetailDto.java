package com.finalassignment.assignment.DTO;

import lombok.Data;

@Data
public class OrderDetailDto {
    private int id;
    private int orderId;
    private int itemId;
    private int quantity;

}
