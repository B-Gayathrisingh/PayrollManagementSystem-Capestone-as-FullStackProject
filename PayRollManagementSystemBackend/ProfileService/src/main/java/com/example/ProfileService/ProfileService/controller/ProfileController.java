package com.example.ProfileService.ProfileService.controller;

import com.example.ProfileService.ProfileService.dto.ProfileDto;
import com.example.ProfileService.ProfileService.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/{employeeId}")
    public ResponseEntity<ProfileDto> getProfile(@PathVariable String employeeId) {
        String cleanId = employeeId.trim(); // remove any hidden newline/space
        ProfileDto profile = profileService.getProfileByEmployeeId(cleanId);
        return ResponseEntity.ok(profile);
    }

}