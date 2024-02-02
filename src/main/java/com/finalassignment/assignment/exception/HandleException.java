package com.finalassignment.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class HandleException {
    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Map<String, String> handleItemNotFound(ItemNotFoundException itemNotFoundException) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error code", "1");
        errorMap.put("Error Msg", itemNotFoundException.getMessage());
        return errorMap;
    }
}
