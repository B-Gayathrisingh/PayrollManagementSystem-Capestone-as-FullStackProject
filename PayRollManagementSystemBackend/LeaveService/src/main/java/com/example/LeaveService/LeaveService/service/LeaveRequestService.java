package com.example.LeaveService.LeaveService.service;

import com.example.LeaveService.LeaveService.dto.LeaveRequestDto;
import com.example.LeaveService.LeaveService.entity.LeaveRequest;
import com.example.LeaveService.LeaveService.repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveRequestService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    public LeaveRequest applyLeave(LeaveRequest leave) {
        leave.setStatus("PENDING");
        return leaveRequestRepository.save(leave);
    }

    public LeaveRequest approveLeave(Long id, String status) {
        LeaveRequest leave = leaveRequestRepository.findById(id).orElseThrow();
        leave.setStatus(status);
        return leaveRequestRepository.save(leave);
    }

    public Optional<LeaveRequest> findById(Long id) {
        return leaveRequestRepository.findById(id);
    }

    public List<LeaveRequest> getAllLeaves() {
        return leaveRequestRepository.findAll();
    }

    public LeaveRequest getLeaveByEmployeeId(String employeeId) {
        return leaveRequestRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new RuntimeException("Leave not found for employeeId: " + employeeId));
    }

}
