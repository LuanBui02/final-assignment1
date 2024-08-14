package com.finalassignment.assignment.exception;

public class DuplicateNameException extends RuntimeException{
    public DuplicateNameException () {
        super("This name is already added");
    }
}
