package com.bni.bni.service;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bni.bni.entity.Profile;
import com.bni.bni.repository.ProfileRepository;

@Service
public class ProfileService {
    
    @Autowired
    private ProfileRepository repo_profile;

    public String addProfile(Long user_id, String first_name, String last_name, String place_of_birth, LocalDate date_of_birth) {
        Profile profile = new Profile();
        profile.setUser_id(user_id);
        profile.setFirst_name(first_name);
        profile.setLast_name(last_name);
        profile.setPlace_of_birth(place_of_birth);
        profile.setDate_of_birth(date_of_birth);
        profile.setCreatedAt(OffsetDateTime.now());
        profile.setUpdatedAt(OffsetDateTime.now());
        repo_profile.save(profile);

        return "Profile added successfully";
    }

    // public String showProfiles(Long user_id) {
    //     Optional<Profile> profile = repo_profile.findByUser_id(user_id);
    //     if (user_id.isPresent() && encoder.matches(password, user.get().getPasswordHash())) {
    //         return jwtUtil.generateToken(username, user.get().getRole());
    //     }

    //     return null; 
    // } 
}