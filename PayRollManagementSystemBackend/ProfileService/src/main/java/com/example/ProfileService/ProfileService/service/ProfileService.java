package com.example.ProfileService.ProfileService.service;

import com.example.ProfileService.ProfileService.client.EmployeeClient;
import com.example.ProfileService.ProfileService.client.LeaveClient;
import com.example.ProfileService.ProfileService.client.PayrollClient;
import com.example.ProfileService.ProfileService.dto.EmployeeDto;
import com.example.ProfileService.ProfileService.dto.LeaveDto;
import com.example.ProfileService.ProfileService.dto.PayrollDto;
import com.example.ProfileService.ProfileService.dto.ProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileService {

    @Autowired
    private EmployeeClient employeeClient;

    @Autowired
    private PayrollClient payrollClient;

    @Autowired
    private LeaveClient leaveClient;

    public ProfileDto getProfileByEmployeeId(String employeeId) {

        // Fetch Employee
        EmployeeDto employee = employeeClient.getEmployeeById(employeeId);

        // Fetch Payrolls for employee
        List<PayrollDto> allPayrolls = payrollClient.getAllPayrolls();
        List<PayrollDto> payrolls = allPayrolls.stream()
                .filter(p -> p.getEmployeeId().equals(employeeId))
                .collect(Collectors.toList());

        // Fetch Leave Requests for employee
        // If you have multiple leave requests, you can modify to return a list
        LeaveDto leave = leaveClient.getLeaveRequestById(employeeId); // assuming employeeId maps to leave id
        List<LeaveDto> leaves = leave != null ? Collections.singletonList(leave) : Collections.emptyList();

        // Construct Profile DTO
        ProfileDto profile = new ProfileDto();
        profile.setEmployee(employee);
        profile.setPayrolls(payrolls);
        profile.setLeaves(leaves);

        return profile;
    }
}