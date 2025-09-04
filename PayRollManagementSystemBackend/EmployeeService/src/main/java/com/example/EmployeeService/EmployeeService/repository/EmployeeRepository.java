package com.example.EmployeeService.EmployeeService.repository;

import com.example.EmployeeService.EmployeeService.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Optional<Employee> findByemployeeId(String employeeId);

    void deleteByEmployeeId(String employeeId);


//    Optional<Employee> findByuserid(String userid);
}
