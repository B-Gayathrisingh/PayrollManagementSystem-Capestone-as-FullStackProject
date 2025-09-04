package com.example.AdminService.AdminService.response;

import com.example.AdminService.AdminService.dto.UserDto;
import com.example.AdminService.AdminService.entity.Role;
import com.example.AdminService.AdminService.entity.User;

import java.util.List;
import java.util.Objects;
import java.util.Set;
public class JwtResponse {
    private String token;
    private String userid;
    private Set<String> roles;

    public JwtResponse(String token, String userid, Set<String> roles) {
        this.token = token;
        this.userid = userid;
        this.roles = roles;
    }

    public JwtResponse(){
        super();
    }

    public JwtResponse(String token, UserDto userid, Set<String> roles) {
    }

    @Override
    public String toString() {
        return "JwtResponse{" +
                "token='" + token + '\'' +
                ", userid='" + userid + '\'' +
                ", roles=" + roles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        JwtResponse that = (JwtResponse) o;
        return Objects.equals(token, that.token) && Objects.equals(userid, that.userid) && Objects.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, userid, roles);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}



