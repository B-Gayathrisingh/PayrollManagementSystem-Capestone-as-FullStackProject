package com.example.AdminService.AdminService.dto;

import java.util.Objects;
import java.util.Set;

public class UserDto {
    private String userid;
    private String email;
    private Set<String> roles;

    public UserDto(String userid, String email, Set<String> roles) {
        this.userid = userid;
        this.email = email;
        this.roles = roles;
    }

    public UserDto() {

    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userid='" + userid + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(userid, userDto.userid) && Objects.equals(email, userDto.email) && Objects.equals(roles, userDto.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, email, roles);
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return getPassword();
    }
}


