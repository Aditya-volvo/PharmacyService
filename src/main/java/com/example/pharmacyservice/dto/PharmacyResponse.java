package com.example.pharmacyservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PharmacyResponse {
    @NotBlank
    private String pharmacyId;
    @NotBlank
    private String pharmacyName;
    @NotBlank
    private String pharmacyGstNo;
    @NotBlank
    private String pharmacyLicenseNo;
    @NotBlank
    private Long adminId;
}
