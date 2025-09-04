package com.example.EmployeeService.EmployeeService.dto;


import java.util.Objects;

public class UserDto {
    private Long id;

    private String userid;

    private String email;

    private String password;

    public UserDto(Long id, String userid, String email, String password) {
        this.id = id;
        this.userid = userid;
        this.email = email;
        this.password = password;
    }
    public UserDto(){
        super();
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", userid=" + userid +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) && Objects.equals(userid, userDto.userid) && Objects.equals(email, userDto.email) && Objects.equals(password, userDto.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userid, email, password);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
