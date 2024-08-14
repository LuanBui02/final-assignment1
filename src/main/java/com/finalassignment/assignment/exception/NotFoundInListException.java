package com.finalassignment.assignment.exception;

public class NotFoundInListException extends RuntimeException{
    public NotFoundInListException() {
        super("Username or password is not correct");
    }
}
