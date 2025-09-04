package com.example.AdminService.AdminService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {


    @Id
    private String name;

    public Role() { }

    public Role(String name) {
        this.name = name;
    }

    public Role(Object o, String roleName) {
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}