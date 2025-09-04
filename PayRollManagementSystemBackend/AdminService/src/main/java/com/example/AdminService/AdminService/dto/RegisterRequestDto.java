package com.example.AdminService.AdminService.dto;

import java.util.Objects;
import java.util.Set;

public class RegisterRequestDto {

    private String email;
    private String password;
    private Set<String> roles;

    public RegisterRequestDto(String email, String password, Set<String> roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public RegisterRequestDto(){
        super();
    }

    @Override
    public String toString() {
        return "RegisterRequestDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RegisterRequestDto that = (RegisterRequestDto) o;
        return Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, roles);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
