package com.example.AdminService.AdminService.repository;

import com.example.AdminService.AdminService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

    Optional<User> findByuserid(String userid);

//    @Query("SELECT u FROM User u WHERE u.id = :id")
//    Optional<User> findByUserid(Long user_id);
}
