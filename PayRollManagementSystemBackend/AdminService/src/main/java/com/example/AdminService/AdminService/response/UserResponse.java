package com.example.AdminService.AdminService.response;

import java.util.Objects;

public class UserResponse {

    private String userid;
    private String email;

    public UserResponse(String userid, String email) {
        this.userid = userid;
        this.email = email;
    }

    public UserResponse(){
        super();
    }

    public <R> UserResponse(String userid, String email, R collect) {
    }


    @Override
    public String toString() {
        return "UserResponse{" +
                "user_id=" + userid +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserResponse that = (UserResponse) o;
        return Objects.equals(userid, that.userid) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, email);
    }


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid= userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
