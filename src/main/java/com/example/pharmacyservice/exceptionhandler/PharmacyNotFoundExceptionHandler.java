package com.example.pharmacyservice.exceptionhandler;

import com.example.pharmacyservice.exception.PharmacyNotFoundException;
import com.example.pharmacyservice.responseentity.PharmacyErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PharmacyNotFoundExceptionHandler {

    @ExceptionHandler(PharmacyNotFoundException.class)
    public ResponseEntity<PharmacyErrorResponse> handlePharmacyNotFoundException(PharmacyNotFoundException ex){
    PharmacyErrorResponse errorResponse = new PharmacyErrorResponse(ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
