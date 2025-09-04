package com.example.EmployeeService.EmployeeService.controller;

import com.example.EmployeeService.EmployeeService.dto.EmployeeDto;
import com.example.EmployeeService.EmployeeService.dto.UserDto;
import com.example.EmployeeService.EmployeeService.entity.Employee;
import com.example.EmployeeService.EmployeeService.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:5173")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    private WebClient webClient;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

//    @PreAuthorize("hasAuthority('EMPLOYEE')")
@PostMapping("/saveemployee")
public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeDto request) {
    Employee employee = employeeService.createEmployee(request);
    return ResponseEntity.ok(employee);
}


    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable String employeeId) {
        String cleanId = employeeId.trim(); // Remove hidden newline/space

        return employeeService.findByEmployeeId(cleanId)
                .map(employee -> {
                    EmployeeDto dto = new EmployeeDto();
                    dto.setId(employee.getId());
                    dto.setFirst_name(employee.getFirst_name());
                    dto.setLast_name(employee.getLast_name());
                    dto.setDob(employee.getDob());
                    dto.setPhone(employee.getPhone());
                    dto.setAddress(employee.getAddress());
                    dto.setDepartment(employee.getDepartment());
                    dto.setDesignation(employee.getDesignation());
                    dto.setSalary(employee.getSalary());
                    dto.setEmployeeId(employee.getEmployeeId());

                    // Safely call AdminService
                    try {
                        UserDto user = webClient
                                .get()
                                .uri("http://localhost:8081/users/{userId}", employee.getUserid())
                                .retrieve()
                                .bodyToMono(UserDto.class)
                                .block();

                        if (user != null) {
                            dto.setEmail(user.getEmail());
                        }
                    } catch (Exception e) {
                        // Log warning but continue
                        System.out.println("AdminService unavailable: " + e.getMessage());
                    }

                    return ResponseEntity.ok(dto);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/allemployees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.findAllEmployees();
        if (employees.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(employees);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable String employeeId,
            @RequestBody Employee updatedEmployee) {
        Employee employee = employeeService.updateEmployee(employeeId, updatedEmployee);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee deleted successfully");
    }
}
