package com.example.ProfileService.ProfileService.dto;

import java.util.List;
import java.util.Objects;

public class ProfileDto {
    private EmployeeDto employee;
    private List<PayrollDto> payrolls;
    private List<LeaveDto> leaves;

    public ProfileDto(EmployeeDto employee, List<PayrollDto> payrolls, List<LeaveDto> leaves) {
        this.employee = employee;
        this.payrolls = payrolls;
        this.leaves = leaves;
    }

    public ProfileDto() {
        super();
    }

    @Override
    public String toString() {
        return "ProfileDto{" +
                "employee=" + employee +
                ", payrolls=" + payrolls +
                ", leaves=" + leaves +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProfileDto that = (ProfileDto) o;
        return Objects.equals(employee, that.employee) && Objects.equals(payrolls, that.payrolls) && Objects.equals(leaves, that.leaves);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee, payrolls, leaves);
    }

    public EmployeeDto getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDto employee) {
        this.employee = employee;
    }

    public List<PayrollDto> getPayrolls() {
        return payrolls;
    }

    public void setPayrolls(List<PayrollDto> payrolls) {
        this.payrolls = payrolls;
    }

    public List<LeaveDto> getLeaves() {
        return leaves;
    }

    public void setLeaves(List<LeaveDto> leaves) {
        this.leaves = leaves;
    }
}