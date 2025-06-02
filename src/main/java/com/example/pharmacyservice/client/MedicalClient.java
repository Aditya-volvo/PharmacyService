package com.example.pharmacyservice.client;


import com.example.pharmacyservice.dto.MedicalDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "medical-service", url = "${medical.service.url}")
public interface MedicalClient {
    @GetMapping("/api/medical/pharmacy/{pharmacyId}")
    List<MedicalDto> getMedicinesByPharmacyId(@PathVariable Long pharmacyId);
}
