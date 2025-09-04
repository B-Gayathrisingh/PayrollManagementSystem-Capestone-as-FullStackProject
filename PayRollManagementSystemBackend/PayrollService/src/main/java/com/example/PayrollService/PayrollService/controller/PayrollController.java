package com.example.PayrollService.PayrollService.controller;


import com.example.PayrollService.PayrollService.dto.APIResponseDto;
import com.example.PayrollService.PayrollService.dto.EmployeeDto;
import com.example.PayrollService.PayrollService.dto.PayrollDto;
import com.example.PayrollService.PayrollService.entity.Payroll;
import com.example.PayrollService.PayrollService.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/payroll")
@CrossOrigin(origins =  "http://localhost:5173/")
public class PayrollController {

    @Autowired
    private PayrollService payrollService;

    @Autowired
    private WebClient webClient;

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/generate")
    public ResponseEntity<PayrollDto> generatePayroll(@RequestBody PayrollDto payrollDto) {
        PayrollDto savedPayroll = payrollService.generatePayroll(payrollDto);
        return ResponseEntity.ok(savedPayroll);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponseDto> getPayrollWithEmployee(@PathVariable Long id) { //, @RequestHeader("Authorization") String authHeader
        return payrollService.getPayrollById(id)
                .map(payroll -> {
                    EmployeeDto employeeDto = webClient.get()
                            .uri("http://localhost:8889/employee/{employeeId}", payroll.getEmployeeId())
//                            .header(HttpHeaders.AUTHORIZATION, authHeader)
                            .retrieve()
                            .bodyToMono(EmployeeDto.class)  // <- map JSON object
                            .block();

//                    EmployeeDto employeeDto = map.get("employeeDto");


                    PayrollDto payrollDto = new PayrollDto(
                            payroll.getId(),
                            payroll.getEmployeeId(),
                            payroll.getBasicSalary(),
                            payroll.getDeductions(),
                            payroll.getNetSalary(),
                            payroll.getPayrollDate()
                    );

                    APIResponseDto responseDto = new APIResponseDto(employeeDto, payrollDto);
                    return ResponseEntity.ok(responseDto);
                })
                .orElse(ResponseEntity.notFound().build());
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Payroll updatePayroll(@PathVariable Long id, @RequestBody Payroll payroll) {
        return payrollService.update(id, payroll);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deletePayroll(@PathVariable Long id) {
        payrollService.delete(id);
    }

    @GetMapping("/allpayrolls")
    public ResponseEntity<List<Payroll>> getAllPayrolls() {
        List<Payroll> payrolls = payrollService.findAllPayrolls();
        if (payrolls.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(payrolls);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Payroll> getPayrollById(@PathVariable Long id) {
//        Optional<Payroll> payroll = payrollService.getPayrollById(id);
//        if (payroll.isPresent()) {
//            return ResponseEntity.ok(payroll.get());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }


    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<Payroll> getPayrollByEmployeeId(@PathVariable String employeeId) {
        return ResponseEntity.ok(payrollService.getPayrollByEmployeeId(employeeId));
    }
}
