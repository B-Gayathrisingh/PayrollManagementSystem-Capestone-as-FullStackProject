package com.example.EmployeeService.EmployeeService.service;


import com.example.EmployeeService.EmployeeService.client.AdminClient;
import com.example.EmployeeService.EmployeeService.dto.EmployeeDto;
import com.example.EmployeeService.EmployeeService.entity.Employee;
import com.example.EmployeeService.EmployeeService.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AdminClient adminClient;

    public EmployeeService(EmployeeRepository employeeRepository, AdminClient adminClient) {
        this.employeeRepository = employeeRepository;
        this.adminClient = adminClient;
    }

    public Employee createEmployee(EmployeeDto request) {
//        String authHeader = "Bearer " + jwtToken.replace("Bearer ", "");
        String userId = adminClient.getUserIdByEmail(request.getEmail());

        if (userId == null || userId.isEmpty()) {
            throw new RuntimeException("User not found in Admin Service for email: " + request.getEmail());
        }

        Employee employee = new Employee();
        employee.setFirst_name(request.getFirst_name());
        employee.setLast_name(request.getLast_name());
        employee.setDob(request.getDob());
        employee.setPhone(request.getPhone());
        employee.setAddress(request.getAddress());
        employee.setDepartment(request.getDepartment());
        employee.setDesignation(request.getDesignation());
        employee.setSalary(request.getSalary());
        employee.setEmployeeId(request.getEmployeeId());
        employee.setUserId(userId);

        return employeeRepository.save(employee);
    }

    public Optional<Employee> findByEmployeeId(String employeeId) {
        return employeeRepository.findByemployeeId(employeeId);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee updateEmployee(String employeeId, Employee updatedEmployee) {
        Employee employee = employeeRepository.findByemployeeId(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + employeeId));

        // update fields (choose the ones you want editable)
        employee.setFirst_name(updatedEmployee.getFirst_name());
        employee.setLast_name(updatedEmployee.getLast_name());
        employee.setDob(updatedEmployee.getDob());
        employee.setPhone(updatedEmployee.getPhone());
        employee.setAddress(updatedEmployee.getAddress());
        employee.setDepartment(updatedEmployee.getDepartment());
        employee.setDesignation(updatedEmployee.getDesignation());
        employee.setSalary(updatedEmployee.getSalary());
        employee.setEmployeeId(updatedEmployee.getEmployeeId());
//        employee.setUserId(userId);

        return employeeRepository.save(employee);
    }


    public void deleteEmployee(String employeeId) {
        Employee employee = employeeRepository.findByemployeeId(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + employeeId));

        employeeRepository.delete(employee); // delete employee
    }

}