package com.example.pharmacyservice.dto;

import java.time.LocalDateTime;

public class TransactionDto {
    private Long transactionId;
    private String transactionType;
    private LocalDateTime dateTime;
    private Long quantity;
    private Long medicineId;
    private Long pharmacyId;
}
