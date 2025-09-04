package com.example.EmployeeService.EmployeeService.client;

import com.example.EmployeeService.EmployeeService.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "ADMINSERVICE",url ="http://localhost:8888/api/auth/")
public interface AdminClient {
    @GetMapping("email/{email}")
    String getUserIdByEmail(@PathVariable("email") String email);
}


