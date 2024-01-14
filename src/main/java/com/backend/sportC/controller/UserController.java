package com.backend.sportC.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.sportC.communication.Request.ProfileRequest;
import com.backend.sportC.communication.Response.ProfileResponse;
import com.backend.sportC.enums.Skill;
import com.backend.sportC.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/profile/{userId}")
    public ResponseEntity<ProfileResponse> getUserProfile(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.getProfileById(userId));
    }

    @PostMapping("/profile/create")
    public ResponseEntity<String> createProfile(@Valid @RequestBody ProfileRequest request) {
        userService.createProfileById(request);
        return ResponseEntity.ok("Profile created successfully");
    }

    @PutMapping("/profile/{profileId}")
    public ResponseEntity<String> updateUserProfile(
            @PathVariable Integer profileId,
            @RequestParam(required = false) Skill skillLevel,
            @RequestParam(required = false) String prefix,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {
        userService.updateProfileById(profileId, skillLevel, prefix, firstName, lastName);
        return ResponseEntity.ok("Profile updated successfully");
    }

}
