package com.example.pharmacyservice.service.serviceimpl;

import com.example.pharmacyservice.dto.PharmacyRequest;
import com.example.pharmacyservice.dto.PharmacyResponse;
import com.example.pharmacyservice.exception.PharmacyNotFoundException;
import com.example.pharmacyservice.mapper.GlobalMapper;
import com.example.pharmacyservice.module.Pharmacy;
import com.example.pharmacyservice.repository.PharmacyRepository;
import com.example.pharmacyservice.responseentity.GlobalResponseEntity;
import com.example.pharmacyservice.service.PharmacyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PharmacyServiceImpl implements PharmacyService {
    private final PharmacyRepository pharmacyRepository;
    private  final GlobalMapper globalMapper;
    private final GlobalResponseEntity globalResponseEntity;

    @Override
    public ResponseEntity<PharmacyResponse> registerPharmacy(PharmacyRequest pharmacyRequest) {
        Pharmacy pharmacy = Pharmacy.builder()
                .pharmacyId(pharmacyRequest.getPharmacyId())
                .pharmacyName(pharmacyRequest.getPharmacyName())
                .pharmacyGstNo(pharmacyRequest.getPharmacyGstNo())
                .pharmacyLicenseNo(pharmacyRequest.getPharmacyLicenseNo())
                .adminId(pharmacyRequest.getAdminId())
                .build();
        Pharmacy saved = pharmacyRepository.save(pharmacy);
        return globalResponseEntity.ok(saved);

    }


    @Override
    public List<PharmacyResponse> getAllPharmacies() {
        List<Pharmacy> pharmacies = pharmacyRepository.findAll();
       return pharmacies.stream().map(globalMapper::mapToPharmacyResponse).toList();
    }

    @Override
    public ResponseEntity<PharmacyResponse> getPharmacyById(String pharmacyId) {
        Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId).orElseThrow( ()-> new PharmacyNotFoundException("Pharmacy with Id :"+pharmacyId+"Not Found"));
        return globalResponseEntity.ok(pharmacy);
    }

    @Override
    public ResponseEntity<PharmacyResponse> upadtePharmacyById(String pharmacyId, @Valid PharmacyRequest pharmacyRequest) {
        Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId).orElseThrow(()->new PharmacyNotFoundException("Pharmacy with Id :"+pharmacyId+"Not Found"));
        pharmacy.setPharmacyId(pharmacyRequest.getPharmacyId());
        pharmacy.setPharmacyName(pharmacyRequest.getPharmacyName());
        pharmacy.setPharmacyGstNo(pharmacyRequest.getPharmacyGstNo());
        pharmacy.setPharmacyLicenseNo(pharmacyRequest.getPharmacyLicenseNo());
        pharmacy.setAdminId(pharmacy.getAdminId());

        Pharmacy saved = pharmacyRepository.save(pharmacy);
        return globalResponseEntity.ok(saved);

    }

    @Override
    public String deletePharmacyById(String pharmacyId) {
        Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId).orElseThrow(()->new PharmacyNotFoundException("Pharmacy with Id :"+pharmacyId+"Not Found"));
        pharmacyRepository.delete( pharmacy);
        return "Pharmacy with id : "+pharmacyId+" is deleted";
    }
}
