package com.example.LeaveService.LeaveService.dto;

import java.util.Objects;

public class APIResponseDto {

    private EmployeeDto employeeDto;

    private LeaveRequestDto leaveRequestDto;

    public APIResponseDto(EmployeeDto employeeDto, LeaveRequestDto leaveRequestDto) {
        this.employeeDto = employeeDto;
        this.leaveRequestDto = leaveRequestDto;
    }

    public APIResponseDto(){
        super();
    }

    @Override
    public String toString() {
        return "APIResponseDto{" +
                "employeeDto=" + employeeDto +
                ", leaveRequestDto=" + leaveRequestDto +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        APIResponseDto that = (APIResponseDto) o;
        return Objects.equals(employeeDto, that.employeeDto) && Objects.equals(leaveRequestDto, that.leaveRequestDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeDto, leaveRequestDto);
    }

    public EmployeeDto getEmployeeDto() {
        return employeeDto;
    }

    public void setEmployeeDto(EmployeeDto employeeDto) {
        this.employeeDto = employeeDto;
    }

    public LeaveRequestDto getLeaveRequestDto() {
        return leaveRequestDto;
    }

    public void setLeaveRequestDto(LeaveRequestDto leaveRequestDto) {
        this.leaveRequestDto = leaveRequestDto;
    }
}
