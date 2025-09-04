package com.example.ProfileService.ProfileService.client;

import com.example.ProfileService.ProfileService.dto.PayrollDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "PAYROLLSERVICE", url = "http://localhost:8890/payroll")
public interface PayrollClient {
    @GetMapping("/{employeeId}")
    PayrollDto getPayrollById(@PathVariable String employeeId);

    @GetMapping("/allpayrolls")
    List<PayrollDto> getAllPayrolls();
}
