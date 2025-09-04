package com.example.AdminService.AdminService.controller;

import com.example.AdminService.AdminService.dto.LoginRequestDto;
import com.example.AdminService.AdminService.dto.RegisterRequestDto;
import com.example.AdminService.AdminService.entity.Role;
import com.example.AdminService.AdminService.entity.User;
import com.example.AdminService.AdminService.repository.RoleRepository;
import com.example.AdminService.AdminService.repository.UserRepository;
import com.example.AdminService.AdminService.response.JwtResponse;
import com.example.AdminService.AdminService.service.AuthService;
import com.example.AdminService.AdminService.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins="http://localhost:5173/")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final RoleRepository roleRepository;

    private final AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private JwtService jwtService;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequestDto request) {
        User user = authService.register(request);
        return ResponseEntity.ok("User registered: " + user.getEmail());
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequestDto request) {
        // Authenticate email + password
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        // Load user from DB
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Generate JWT
        String token = jwtService.generateToken(user.getEmail(), user.getRoles());


        // Extract roles as simple strings: ADMIN / EMPLOYEE
        Set<String> roles = user.getRoles().stream()
                .map(role -> role.getName()) // no ROLE_ prefix
                .collect(Collectors.toSet());

        // Return JWT, userId, roles
        return new JwtResponse(token, user.getUserid(), roles);

    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/roles/create")
    public ResponseEntity<String> createRole(@RequestBody Role role) {
        if(roleRepository.existsById(role.getName())) {
            return ResponseEntity.badRequest().body("Role exists: " + role.getName());
        }
        roleRepository.save(role);
        return ResponseEntity.ok("Role created: " + role.getName());
    }

    @GetMapping("/roles")
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

//    @PreAuthorize("hasRole('ADMIN')")
@GetMapping("/email/{email}")
public String getUserIdByEmail(@PathVariable String email) {
    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));
    return user.getUserid();
}


}