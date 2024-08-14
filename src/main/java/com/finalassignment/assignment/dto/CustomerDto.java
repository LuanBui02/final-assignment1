package com.finalassignment.assignment.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private Integer id;
    private String username;
    private String password;
    private int type;
    @JsonIgnore
    private List<CartDto> cartsDto;
    @JsonIgnore
    private List<OrderDto> ordersDto;
}
