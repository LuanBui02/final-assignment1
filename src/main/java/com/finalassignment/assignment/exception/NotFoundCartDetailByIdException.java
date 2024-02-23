package com.finalassignment.assignment.exception;

public class NotFoundCartDetailByIdException extends RuntimeException{
    public NotFoundCartDetailByIdException() {
        super("Can not find CartDetail");
    }
}
