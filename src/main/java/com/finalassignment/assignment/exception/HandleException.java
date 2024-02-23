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
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleCustomerEmpty(CustomerNotFoundException customerNotFoundException) {
        ErrorMessage error = new ErrorMessage("Error: 2", customerNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(CartNotFoundByIdException.class)
    public ResponseEntity<ErrorMessage> handleCart(CartNotFoundByIdException cartNotFoundByIdException) {
        ErrorMessage error = new ErrorMessage("Error: 3", cartNotFoundByIdException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(NotFoundCartDetailByIdException.class)
    public ResponseEntity<ErrorMessage> handleCartDetail(NotFoundCartDetailByIdException notFoundCartDetailByIdException) {
        ErrorMessage error = new ErrorMessage("Error: 4", notFoundCartDetailByIdException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleOrderNotFound(OrderNotFoundException orderNotFoundException) {
        ErrorMessage error = new ErrorMessage("Error: 5", orderNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
