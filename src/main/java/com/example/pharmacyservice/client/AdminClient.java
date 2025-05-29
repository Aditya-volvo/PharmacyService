package com.example.pharmacyservice.client;

import com.example.pharmacyservice.dto.AdminResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("admin-service")
public interface AdminClient {

    @GetMapping("/api/admin/{id}")
    AdminResponseDto getAdminById(@PathVariable("id") Long id);
}
