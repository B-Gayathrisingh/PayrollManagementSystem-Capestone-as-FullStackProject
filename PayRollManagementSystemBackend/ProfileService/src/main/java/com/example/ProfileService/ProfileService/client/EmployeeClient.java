package com.example.ProfileService.ProfileService.client;

import com.example.ProfileService.ProfileService.dto.EmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "EMPLOYEESERVICE", url = "http://localhost:8889/employee")
public interface EmployeeClient {
    @GetMapping("/{employeeId}")
    EmployeeDto getEmployeeById(@PathVariable String employeeId);
}
