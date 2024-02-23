package com.finalassignment.assignment.exception;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(int customerId) {
        super(String.format( "There is no customer with id: %s", customerId));
    }
}
