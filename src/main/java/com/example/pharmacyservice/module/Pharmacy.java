package com.example.pharmacyservice.module;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pharmacy_table")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Pharmacy {
    @Id
    @Column(name = "p_id")
    private String pharmacyId;
    @Column(name = "p_name")
    private String pharmacyName;
    @Column(name = "p_gst_no")
    private String pharmacyGstNo;
    @Column(name = "p_license_no")
    private String pharmacyLicenseNo;
    @Column(name = "admin_id")
    private Long adminId;
}
