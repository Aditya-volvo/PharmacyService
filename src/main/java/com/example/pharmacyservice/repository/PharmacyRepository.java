package com.example.pharmacyservice.repository;

import com.example.pharmacyservice.module.Pharmacy;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyRepository extends JpaRepository<Pharmacy,String> {
    boolean existsByAdminId(@NotBlank Long adminId);
}
