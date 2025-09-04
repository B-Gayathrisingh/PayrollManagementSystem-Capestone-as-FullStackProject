package com.example.AdminService.AdminService.service;

import com.example.AdminService.AdminService.dto.LoginRequestDto;
import com.example.AdminService.AdminService.dto.RegisterRequestDto;
import com.example.AdminService.AdminService.entity.Role;
import com.example.AdminService.AdminService.entity.User;
import com.example.AdminService.AdminService.repository.RoleRepository;
import com.example.AdminService.AdminService.repository.UserRepository;
import com.example.AdminService.AdminService.response.JwtResponse;
import com.example.AdminService.AdminService.response.UserResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthService {
    
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private String jwtExpiration;

    @Autowired
    private RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository, JwtService jwtUtil, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public String login(LoginRequestDto req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
        );

        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return jwtUtil.generateToken(user.getEmail(), user.getRoles());
    }
        public String generateToken(User user){
        return Jwts.builder().setSubject(user.getEmail())
                .claim("roles",user.getRoles())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+jwtExpiration))
                .signWith(SignatureAlgorithm.HS256,secret.getBytes(StandardCharsets.UTF_8))
                .compact();

    }

    public User register(RegisterRequestDto dto) {
        if(userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        Set<Role> roles = dto.getRoles().stream()
                .map(r -> roleRepository.findByName(r.toUpperCase())
                        .orElseThrow(() -> new RuntimeException("Role not found: " + r)))
                .collect(Collectors.toSet());


        user.setRoles(roles);
        return userRepository.save(user);
    }

    public Optional<User> getUserById(String userid) {
        return userRepository.findByuserid(userid);
    }


    public UserResponse getUserIdByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserResponse(user.getUserid(),
        user.getEmail(),user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));
    }


}
