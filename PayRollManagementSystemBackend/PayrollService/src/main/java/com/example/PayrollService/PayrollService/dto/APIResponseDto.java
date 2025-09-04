package com.example.PayrollService.PayrollService.dto;

import java.util.Objects;

public class APIResponseDto {

    private EmployeeDto employeeDto;

    private PayrollDto payrollDto;

    public APIResponseDto(EmployeeDto employeeDto, PayrollDto payrollDto) {
        this.employeeDto = employeeDto;
        this.payrollDto = payrollDto;
    }

    @Override
    public String toString() {
        return "APIResponseDto{" +
                "employeeDto=" + employeeDto +
                ", payrollDto=" + payrollDto +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        APIResponseDto that = (APIResponseDto) o;
        return Objects.equals(employeeDto, that.employeeDto) && Objects.equals(payrollDto, that.payrollDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeDto, payrollDto);
    }

    public EmployeeDto getEmployeeDto() {
        return employeeDto;
    }

    public void setEmployeeDto(EmployeeDto employeeDto) {
        this.employeeDto = employeeDto;
    }

    public PayrollDto getPayrollDto() {
        return payrollDto;
    }

    public void setPayrollDto(PayrollDto payrollDto) {
        this.payrollDto = payrollDto;
    }
}
