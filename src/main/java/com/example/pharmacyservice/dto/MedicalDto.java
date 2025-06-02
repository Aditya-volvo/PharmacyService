package com.example.pharmacyservice.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MedicalDto {
    private String medicineId;
    private String medicineName;
    private String medicineCategory;
    private List<String> medicineIngredients;
    private int dosageInMg;
    private LocalDate expireDate;
    private Double price;
}
