package com.finalassignment.assignment.exception;

public class ItemDuplicatedException extends RuntimeException{
    public ItemDuplicatedException() {
        super("Item is already added");
    }
}
