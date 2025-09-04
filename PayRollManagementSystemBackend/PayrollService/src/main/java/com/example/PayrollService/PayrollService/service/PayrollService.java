package com.example.PayrollService.PayrollService.service;

import com.example.PayrollService.PayrollService.dto.PayrollDto;
import com.example.PayrollService.PayrollService.entity.Payroll;
import com.example.PayrollService.PayrollService.repository.PayrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PayrollService {

    @Autowired
    private PayrollRepository payrollRepository;

    public PayrollDto generatePayroll(PayrollDto payrollDto) {

        double basicSalary = payrollDto.getBasicSalary() != null ? payrollDto.getBasicSalary() : 0.0;
        double deductions = payrollDto.getDeductions() != null ? payrollDto.getDeductions() : 0.0;
        double netSalary = basicSalary - deductions;

        Payroll payroll = new Payroll();
        payroll.setEmployeeId(payrollDto.getEmployeeId()); // can be null
        payroll.setBasicSalary(basicSalary);
        payroll.setDeductions(deductions);
        payroll.setNetSalary(netSalary);
        payroll.setPayrollDate(payrollDto.getPayrollDate()); // can be null

        Payroll savedPayroll = payrollRepository.save(payroll);

        return new PayrollDto(
                savedPayroll.getId(),
                savedPayroll.getEmployeeId(),
                savedPayroll.getBasicSalary(),
                savedPayroll.getDeductions(),
                savedPayroll.getNetSalary(),
                savedPayroll.getPayrollDate()
        );
    }

    public Optional<Payroll> getPayrollById(Long id) {
        return payrollRepository.findById(id); // returns null if not found
    }

    public List<Payroll> findAllPayrolls() {
        return payrollRepository.findAll(); // returns List<Payroll>
    }

    public Payroll update(Long id, Payroll payroll) {
        Payroll existing = payrollRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payroll not found with id " + id));
        existing.setBasicSalary(payroll.getBasicSalary());
        existing.setDeductions(payroll.getDeductions());
        existing.setNetSalary(payroll.getBasicSalary() - payroll.getDeductions());
        existing.setPayrollDate(payroll.getPayrollDate());
        return payrollRepository.save(existing);
    }

    public void delete(Long id) {
        Payroll existing = payrollRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payroll not found with id " + id));
        payrollRepository.delete(existing);
    }

    public Payroll getPayrollByEmployeeId(String employeeId) {
        return payrollRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new RuntimeException("Payroll not found for employeeId: " + employeeId));
    }

}
