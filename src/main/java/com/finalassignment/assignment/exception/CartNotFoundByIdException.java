package com.finalassignment.assignment.exception;

import com.finalassignment.assignment.model.Cart;

public class CartNotFoundByIdException extends RuntimeException{
    public CartNotFoundByIdException(int customerId){
        super(String.format("Cannot found cart with CustomerId: %s", customerId));
    }
}
