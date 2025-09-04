package com.example.EmployeeService.EmployeeService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class EmployeeDto {

    private Long id;
    @JsonProperty("firstName")
    private String first_name;
    @JsonProperty("lastName")
    private String last_name;
    private String dob;
    private String phone;
    private String address;
    private String department;
    private String designation;
    private Double Salary;
    private String employeeId;
    private String email;


    public EmployeeDto(Long id, String first_name, String last_name, String dob, String phone, String address, String department, String designation, Double salary, String employeeId, String email) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.dob = dob;
        this.phone = phone;
        this.address = address;
        this.department = department;
        this.designation = designation;
        Salary = salary;
        this.employeeId = employeeId;
        this.email = email;
    }

    public EmployeeDto(){
        super();
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", dob='" + dob + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", department='" + department + '\'' +
                ", designation='" + designation + '\'' +
                ", Salary=" + Salary +
                ", employeeId='" + employeeId + '\'' +
                ", email=" + email +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDto that = (EmployeeDto) o;
        return Objects.equals(id, that.id) && Objects.equals(first_name, that.first_name) && Objects.equals(last_name, that.last_name) && Objects.equals(dob, that.dob) && Objects.equals(phone, that.phone) && Objects.equals(address, that.address) && Objects.equals(department, that.department) && Objects.equals(designation, that.designation) && Objects.equals(Salary, that.Salary) && Objects.equals(employeeId, that.employeeId) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, last_name, dob, phone, address, department, designation, Salary, employeeId, email);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Double getSalary() {
        return Salary;
    }

    public void setSalary(Double salary) {
        Salary = salary;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

