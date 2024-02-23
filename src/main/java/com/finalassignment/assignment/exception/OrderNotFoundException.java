package com.finalassignment.assignment.exception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(int customerId) {
        super(String.format("There is no order with customerId: %s",customerId));
    }

}
