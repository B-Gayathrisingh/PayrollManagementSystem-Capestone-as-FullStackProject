package com.example.ProfileService.ProfileService.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Objects;

public class PayrollDto {

    private Long id;
    private String employeeId;
    private Double basicSalary;
    private Double deductions;
    private Double netSalary;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate payrollDate;

    public PayrollDto(Long id, String employeeId, Double basicSalary, Double deductions, Double netSalary, LocalDate payrollDate) {
        this.id = id;
        this.employeeId = employeeId;
        this.basicSalary = basicSalary;
        this.deductions = deductions;
        this.netSalary = netSalary;
        this.payrollDate = payrollDate;
    }


    public PayrollDto(){
        super();
    }

//    public PayrollDto(Long id, String employeeId, Double basicSalary, Double deductions, Double netSalary, LocalDate payrollDate) {
//        // currently empty!
//    }


    @Override
    public String toString() {
        return "PayrollDto{" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", basicSalary=" + basicSalary +
                ", deductions=" + deductions +
                ", netSalary=" + netSalary +
                ", payrollDate=" + payrollDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PayrollDto that = (PayrollDto) o;
        return Objects.equals(id, that.id) && Objects.equals(employeeId, that.employeeId) && Objects.equals(basicSalary, that.basicSalary) && Objects.equals(deductions, that.deductions) && Objects.equals(netSalary, that.netSalary) && Objects.equals(payrollDate, that.payrollDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeId, basicSalary, deductions, netSalary, payrollDate);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(Double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public Double getDeductions() {
        return deductions;
    }

    public void setDeductions(Double deductions) {
        this.deductions = deductions;
    }

    public Double getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(Double netSalary) {
        this.netSalary = netSalary;
    }

    public LocalDate getPayrollDate() {
        return payrollDate;
    }

    public void setPayrollDate(LocalDate payrollDate) {
        this.payrollDate = payrollDate;
    }
}

