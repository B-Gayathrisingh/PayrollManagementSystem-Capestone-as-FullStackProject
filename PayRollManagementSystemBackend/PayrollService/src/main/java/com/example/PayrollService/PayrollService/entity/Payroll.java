package com.example.PayrollService.PayrollService.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "payroll_details")
public class Payroll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String employeeId;
    private Double basicSalary;
    private Double deductions;
    private Double netSalary;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate payrollDate;

    public Payroll(Long id, String employeeId, Double basicSalary, Double deductions, Double netSalary, LocalDate payrollDate) {
        this.id = id;
        this.employeeId = employeeId;
        this.basicSalary = basicSalary;
        this.deductions = deductions;
        this.netSalary = netSalary;
        this.payrollDate = payrollDate;
    }

    public Payroll(){
        super();
    }

    public Payroll(String employeeId, Double basicSalary, Double deductions, Double netSalary, LocalDate payrollDate) {
    }

    @Override
    public String toString() {
        return "Payroll{" +
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
        Payroll payroll = (Payroll) o;
        return Objects.equals(id, payroll.id) && Objects.equals(employeeId, payroll.employeeId) && Objects.equals(basicSalary, payroll.basicSalary) && Objects.equals(deductions, payroll.deductions) && Objects.equals(netSalary, payroll.netSalary) && Objects.equals(payrollDate, payroll.payrollDate);
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
