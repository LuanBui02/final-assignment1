package com.finalassignment.assignment.exception;

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException(int id) {
        super(String.format("Cannot found item with id %d", id));
    }
}
