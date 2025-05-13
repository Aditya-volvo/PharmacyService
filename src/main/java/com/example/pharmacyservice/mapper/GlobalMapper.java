package com.example.pharmacyservice.mapper;


import com.example.pharmacyservice.dto.PharmacyResponse;
import com.example.pharmacyservice.module.Pharmacy;

public class GlobalMapper {
    public PharmacyResponse mapToPharmacyResponse(Pharmacy pharmacy) {
        return  PharmacyResponse.builder()
                .pharmacyId(pharmacy.getPharmacyId())
                .pharmacyName(pharmacy.getPharmacyName())
                .pharmacyGstNo(pharmacy.getPharmacyGstNo())
                .pharmacyLicenseNo(pharmacy.getPharmacyLicenseNo())
                .adminId(pharmacy.getAdminId())
                .build();
    }
}
