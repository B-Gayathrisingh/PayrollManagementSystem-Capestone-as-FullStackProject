package com.example.AdminService.AdminService.repository;


import com.example.AdminService.AdminService.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface  RoleRepository extends JpaRepository<Role,String> {

    Optional<Role> findByName(String name);
}
