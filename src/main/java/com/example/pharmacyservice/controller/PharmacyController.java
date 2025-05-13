package com.example.pharmacyservice.controller;

import com.example.pharmacyservice.dto.PharmacyRequest;
import com.example.pharmacyservice.dto.PharmacyResponse;
import com.example.pharmacyservice.service.PharmacyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pharmacy")
@RequiredArgsConstructor
public class PharmacyController {
    private final PharmacyService pharmacyService;

    @PostMapping
    public ResponseEntity<PharmacyResponse> registerPharmacy(@RequestBody @Valid PharmacyRequest pharmacyRequest){
      return   pharmacyService.registerPharmacy(pharmacyRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PharmacyResponse> getAllPharmacies(){
        return pharmacyService.getAllPharmacies();
    }

    @GetMapping("/{pharmacyId}")
    public ResponseEntity<PharmacyResponse> getPharmacyById(@PathVariable String pharmacyId){
        return pharmacyService.getPharmacyById(pharmacyId);
    }

    @PutMapping("/{pharmacyId}")
    public ResponseEntity<PharmacyResponse> updatePharmacyById(@PathVariable String pharmacyId,@RequestBody @Valid PharmacyRequest pharmacyRequest){
        return pharmacyService.upadtePharmacyById(pharmacyId,pharmacyRequest);
    }

    @DeleteMapping("/{pharmacyId}")
    public  String deletePharmacyById(@PathVariable String pharmacyId){
        return  pharmacyService.deletePharmacyById(pharmacyId);
    }

}
