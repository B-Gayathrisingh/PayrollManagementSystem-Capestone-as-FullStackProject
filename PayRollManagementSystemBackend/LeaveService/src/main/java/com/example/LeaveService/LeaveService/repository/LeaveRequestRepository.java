package com.example.LeaveService.LeaveService.repository;

import com.example.LeaveService.LeaveService.entity.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest,Long> {

    Optional<LeaveRequest> findByEmployeeId(String employeeId);

}
