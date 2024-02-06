package com.finalassignment.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleException {

    @ExceptionHandler(ItemNotFoundByIdException.class)
    public ResponseEntity<ErrorMessage> handleItemNotFoundById(ItemNotFoundByIdException itemNotFoundByIdException) {
        ErrorMessage error = new ErrorMessage("Error: 2", itemNotFoundByIdException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleItemNotFound(ItemNotFoundException itemNotFoundException) {
        ErrorMessage error = new ErrorMessage("Error: 1", "There is no item found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
