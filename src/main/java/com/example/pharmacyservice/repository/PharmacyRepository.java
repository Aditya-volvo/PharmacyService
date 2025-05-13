package com.example.pharmacyservice.repository;

import com.example.pharmacyservice.module.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyRepository extends JpaRepository<Pharmacy,String> {
}
