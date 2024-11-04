package com.inventory.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException ex,WebRequest request){
        Map<String,Object> responseBody=new HashMap<>();
        responseBody.put("errorCode",ex.getErrorCode());
        responseBody.put("message",ex.getMessage());
        responseBody.put("status",ex.getStatus().value());
        return  new ResponseEntity<>(responseBody,ex.getStatus());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex,WebRequest request){
        Map<String,Object> responseBody=new HashMap<>();
        responseBody.put("errorCode",ex.getErrorCode());
        responseBody.put("message",ex.getMessage());
        responseBody.put("status",ex.getStatus().value());
        return new ResponseEntity<>(responseBody,ex.getStatus());
    }

    // Handle generic exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>("Internal Server Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}

