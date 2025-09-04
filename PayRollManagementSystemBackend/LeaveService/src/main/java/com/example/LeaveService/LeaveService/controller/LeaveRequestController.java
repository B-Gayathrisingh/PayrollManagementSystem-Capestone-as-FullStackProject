package com.example.LeaveService.LeaveService.controller;

import com.example.LeaveService.LeaveService.dto.APIResponseDto;
import com.example.LeaveService.LeaveService.dto.EmployeeDto;
import com.example.LeaveService.LeaveService.dto.LeaveRequestDto;
import com.example.LeaveService.LeaveService.entity.LeaveRequest;
import com.example.LeaveService.LeaveService.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/leaveRequest")
@CrossOrigin(origins = "http://localhost:5173/")
public class LeaveRequestController {

    @Autowired
    private LeaveRequestService leaveRequestService;

    @Autowired
    private WebClient webClient;

//    @PreAuthorize("hasRole('EMPLOYEE')")
    @PostMapping("/apply")
    public LeaveRequest apply(@RequestBody LeaveRequest leave) {
        return leaveRequestService.applyLeave(leave);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public LeaveRequest update(@PathVariable Long id, @RequestBody LeaveRequestDto leaveRequestDto) {
        return leaveRequestService.approveLeave(id, leaveRequestDto.getStatus());
    }


//    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    @GetMapping("/{id}")
    public ResponseEntity<APIResponseDto> getPayrollWithEmployee(@PathVariable Long id) {
        return leaveRequestService.findById(id)
                .map(leaveRequest -> {
                    EmployeeDto employeeDto = webClient.get()
                            .uri("http://localhost:8889/employee/{id}", leaveRequest.getEmployeeId())
                            .retrieve()
                            .bodyToMono(EmployeeDto.class)
                            .block();


                    LeaveRequestDto leaveRequestDto = new LeaveRequestDto(
                            leaveRequest.getId(),
                            leaveRequest.getEmployeeId(),
                            leaveRequest.getStartDate(),
                            leaveRequest.getEndDate(),
                            leaveRequest.getLeaveType(),
                            leaveRequest.getStatus()

                    );

                    APIResponseDto responseDto = new APIResponseDto(employeeDto, leaveRequestDto);
                    return ResponseEntity.ok(responseDto);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/allleaves")
    public List<LeaveRequest> getAllLeaves() {
        return leaveRequestService.getAllLeaves();
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<LeaveRequest> getLeaveRequestById(@PathVariable Long id) {
//        Optional<LeaveRequest> leaveRequest = leaveRequestService.findById(id);
//        if (leaveRequest.isPresent()) {
//            return ResponseEntity.ok(leaveRequest.get());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }


    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<LeaveRequest> getLeaveByEmployeeId(@PathVariable String employeeId) {
        return ResponseEntity.ok(leaveRequestService.getLeaveByEmployeeId(employeeId));
    }

}
