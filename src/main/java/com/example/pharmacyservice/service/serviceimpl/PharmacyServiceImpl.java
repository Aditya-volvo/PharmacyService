package com.example.pharmacyservice.service.serviceimpl;

import com.example.pharmacyservice.client.AdminClient;
import com.example.pharmacyservice.client.MedicalClient;
import com.example.pharmacyservice.dto.MedicalDto;
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
public class PharmacyServiceImpl implements PharmacyService , MedicalClient {
    private final PharmacyRepository pharmacyRepository;
    private  final GlobalMapper globalMapper;
    private final GlobalResponseEntity globalResponseEntity;
    private final AdminClient adminClient;
    private final MedicalClient medicalClient;

    @Override
    public ResponseEntity<PharmacyResponse> registerPharmacy(PharmacyRequest pharmacyRequest) {
//Ensure the Admin exists (Feign call toAuth service)
        adminClient.getAdminById(pharmacyRequest.getAdminId());
//        Enfore one-to-one : on other pharmacy for this admin
        if(pharmacyRepository.existsByAdminId(pharmacyRequest.getAdminId())){
            throw new IllegalStateException(
                    "Admin"+ pharmacyRequest.getAdminId()+" already has a pharmacy");
        }

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

    @Override
    public List<MedicalDto> getMedicinesByPharmacyId(Long pharmacyId) {
        // Ensure the pharmacy exists
        pharmacyRepository.findById(String.valueOf(pharmacyId))
                .orElseThrow(() -> new PharmacyNotFoundException("Pharmacy with ID: " + pharmacyId + " Not Found"));

        // Call the MedicalClient to get medicines by pharmacy ID
        return medicalClient.getMedicinesByPharmacyId(pharmacyId);
    }
}
