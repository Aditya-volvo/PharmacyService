package com.example.pharmacyservice.exception;

public class PharmacyNotFoundException extends RuntimeException{
    public PharmacyNotFoundException(String message){
        super(message);
    }
}
