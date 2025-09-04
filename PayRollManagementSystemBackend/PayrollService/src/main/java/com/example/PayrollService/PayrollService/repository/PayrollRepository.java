package com.example.PayrollService.PayrollService.repository;

import com.example.PayrollService.PayrollService.entity.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll,Long> {

    Payroll findByid(Long id);

    Optional<Payroll> findByEmployeeId(String employeeId);
}
