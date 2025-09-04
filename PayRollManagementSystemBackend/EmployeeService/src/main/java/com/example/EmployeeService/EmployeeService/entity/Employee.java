package com.example.EmployeeService.EmployeeService.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @SequenceGenerator(
//            name = "employee_seq",
//            sequenceName = "e/mployee_sequence",
//            allocationSize = 1
//    )
    private Long id;
    private String first_name;
    private String last_name;
    private String dob;
    private String phone;
    private String address;
    private String department;
    private String designation;
    private Double Salary;
    private String employeeId;
    private String userid;

    public Employee(Long id, String first_name, String last_name, String dob, String phone, String address, String department, String designation, Double salary, String employeeId, String userid) {
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
        this.userid = userid;
    }
    public Employee(){
        super();
    }

    @Override
    public String toString() {
        return "Employee{" +
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
                ", userid=" + userid +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(first_name, employee.first_name) && Objects.equals(last_name, employee.last_name) && Objects.equals(dob, employee.dob) && Objects.equals(phone, employee.phone) && Objects.equals(address, employee.address) && Objects.equals(department, employee.department) && Objects.equals(designation, employee.designation) && Objects.equals(Salary, employee.Salary) && Objects.equals(employeeId, employee.employeeId) && Objects.equals(userid, employee.userid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, last_name, dob, phone, address, department, designation, Salary, employeeId, userid);
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

    public String getUserid() {
        return userid;
    }

    public void setUserId(Long userId) {

    }

    public void setUserId(String userId) {
        {
            this.userid = userId;
        }
    }
}

