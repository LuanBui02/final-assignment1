package com.finalassignment.assignment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.finalassignment.assignment.model.Cart;
import com.finalassignment.assignment.model.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDetailDto {
    private int id;
    private Cart cart;
    private Item item;
    private int quantity;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateAdded;
}
