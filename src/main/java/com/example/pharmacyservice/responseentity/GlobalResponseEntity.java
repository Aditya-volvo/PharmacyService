package com.example.pharmacyservice.responseentity;

import com.example.pharmacyservice.dto.PharmacyResponse;
import com.example.pharmacyservice.module.Pharmacy;
import org.springframework.http.ResponseEntity;

public class GlobalResponseEntity {
    public ResponseEntity<PharmacyResponse> ok(Pharmacy pharmacy){
        return ResponseEntity.ok(
                new PharmacyResponse(
                        pharmacy.getPharmacyId(),
                        pharmacy.getPharmacyName(),
                        pharmacy.getPharmacyGstNo(),
                        pharmacy.getPharmacyLicenseNo(),
                        pharmacy.getAdminId()
                ));
    }
}
