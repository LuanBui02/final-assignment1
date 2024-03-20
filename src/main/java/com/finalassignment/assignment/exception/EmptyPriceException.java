package com.finalassignment.assignment.exception;

public class EmptyPriceException extends RuntimeException{
    public EmptyPriceException() {
        super("Can not get empty price");
    }
}
