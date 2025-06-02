package com.example.pharmacyservice.client;

import com.example.pharmacyservice.dto.TransactionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "transaction-service", url = "${transaction.service.url}")
public interface TransactionServiceClient {
    @GetMapping("/api/pharmacy/transaction/{pharmacyId}")
    List<TransactionDto> getTransactionByPharmacyId(Long pharmacyId);
}
