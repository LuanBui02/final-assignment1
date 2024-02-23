package com.finalassignment.assignment.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private int id;
    private String name;
    private int price;
    @JsonIgnore
    private List<CartDetailDto> cartDetailsDto;
    @JsonIgnore
    private List<OrderDetailDto> orderDetailsDto;

}

