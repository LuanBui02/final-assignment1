package com.finalassignment.assignment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.finalassignment.assignment.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private int id;
    private Customer customer;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date orderDate;
    private boolean isComplete;
    private Set<OrderDetailDto> orderDetailsDto;
}
