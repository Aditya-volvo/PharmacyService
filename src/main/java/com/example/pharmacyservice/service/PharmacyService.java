package com.example.pharmacyservice.service;

import com.example.pharmacyservice.dto.PharmacyRequest;
import com.example.pharmacyservice.dto.PharmacyResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PharmacyService {
    ResponseEntity<PharmacyResponse> registerPharmacy(@Valid PharmacyRequest pharmacyRequest);

    List<PharmacyResponse> getAllPharmacies();

    ResponseEntity<PharmacyResponse> getPharmacyById(String pharmacyId);

    ResponseEntity<PharmacyResponse> upadtePharmacyById(String pharmacyId, @Valid PharmacyRequest pharmacyRequest);

    String deletePharmacyById(String pharmacyId);
}
