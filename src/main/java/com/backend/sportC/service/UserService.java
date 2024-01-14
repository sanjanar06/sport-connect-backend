package com.backend.sportC.service;

import org.springframework.stereotype.Service;

import com.backend.sportC.communication.Request.ProfileRequest;
import com.backend.sportC.communication.Response.ProfileResponse;
import com.backend.sportC.entity.Profile;
import com.backend.sportC.entity.User;
import com.backend.sportC.enums.Skill;
import com.backend.sportC.repository.ProfileRepository;
import com.backend.sportC.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    public ProfileResponse getProfileById(Integer userId) {
        // Add custom exceptions
        User user = userRepository.findById(userId).orElseThrow();

        return ProfileResponse.builder()
                .firstName(user.getProfile().getFirstName())
                .lastName(user.getProfile().getLastName())
                .skillLevel(user.getProfile().getSkillLevel())
                .build();
    }

    public void createProfileById(ProfileRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow();

        if (user.getProfile() == null) {
            Profile profile = Profile.builder()
                    .prefix(request.getPrefix())
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .skillLevel(request.getSkillLevel())
                    .user(user)
                    .build();

            profileRepository.save(profile);
            user.setProfile(profile);
            userRepository.save(user);
        }

    }

    public void updateProfileById(Integer profileId, Skill newSkillLevel, String prefix, String firstName,
            String lastName) {
        Profile profile = profileRepository.findById(profileId).orElseThrow();

        // Update fields only if they are provided in the request
        if (prefix != null) {
            profile.setPrefix(prefix);
        }

        if (firstName != null) {
            profile.setFirstName(firstName);
        }

        if (lastName != null) {
            profile.setLastName(lastName);
        }

        if (newSkillLevel != null) {
            profile.setSkillLevel(newSkillLevel);
        }
        profileRepository.save(profile);
    }
}
