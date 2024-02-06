package com.finalassignment.assignment.exception;

public class ItemNotFoundByIdException extends RuntimeException{
    public ItemNotFoundByIdException(int id) {
        super(String.format("Cannot found item with id %d", id));
    }
}
