package com.example.spring_recap.controllers.advice;

import com.example.spring_recap.controllers.dtos.ResponsePayload;
import com.example.spring_recap.exceptions.InvoiceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(InvoiceNotFoundException.class)
    public ResponseEntity<ResponsePayload<String>> handleInvoiceNotFoundException(InvoiceNotFoundException e) {
        return new ResponseEntity<>(new ResponsePayload<>(null, e.getMessage()),
                HttpStatus.NOT_FOUND);
    }
}
