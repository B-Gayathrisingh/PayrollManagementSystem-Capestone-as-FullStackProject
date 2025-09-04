package com.example.AdminService.AdminService.service;


import com.example.AdminService.AdminService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerDetailsService implements UserDetailsService {
    private final UserRepository users;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var u = users.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var auths = u.getRoles().stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(u.getEmail(), u.getPassword(), auths);
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return users.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName())) // keep EMPLOYEE as-is
//                .collect(Collectors.toList());
//    }
}

