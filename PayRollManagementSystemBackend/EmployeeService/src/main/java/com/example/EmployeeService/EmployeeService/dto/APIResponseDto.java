package com.example.EmployeeService.EmployeeService.dto;

import java.util.Objects;

public class APIResponseDto {

    private UserDto userDto;

    private EmployeeDto employeeDto;

    public APIResponseDto(UserDto userDto, EmployeeDto employeeDto) {
        this.userDto = userDto;
        this.employeeDto = employeeDto;
    }

    public APIResponseDto(){
        super();
    }

    @Override
    public String toString() {
        return "APIResponseDto{" +
                "userDto=" + userDto +
                ", employeeDto=" + employeeDto +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        APIResponseDto that = (APIResponseDto) o;
        return Objects.equals(userDto, that.userDto) && Objects.equals(employeeDto, that.employeeDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userDto, employeeDto);
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public EmployeeDto getEmployeeDto() {
        return employeeDto;
    }

    public void setEmployeeDto(EmployeeDto employeeDto) {
        this.employeeDto = employeeDto;
    }
}
