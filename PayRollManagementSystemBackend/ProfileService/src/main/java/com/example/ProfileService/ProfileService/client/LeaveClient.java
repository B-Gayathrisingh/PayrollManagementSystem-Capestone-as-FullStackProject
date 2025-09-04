package com.example.ProfileService.ProfileService.client;

import com.example.ProfileService.ProfileService.dto.LeaveDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "LEAVESERVICE", url = "http://localhost:8891/leaveRequest")
public interface LeaveClient {
    @GetMapping("/employee/{employeeId}")
    LeaveDto getLeaveRequestById(@PathVariable String employeeId);
}
