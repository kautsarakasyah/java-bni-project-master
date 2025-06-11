package com.bni.bni.controller;

import com.bni.bni.entity.Profiles;
import com.bni.bni.service.ProfilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/profiles")
public class ProfilesController {

    @Autowired
    private ProfilesService profilesService;

    @PostMapping
    public ResponseEntity<?> createProfile(@RequestBody Profiles profile) {
        Profiles created = profilesService.createProfile(profile);
        return ResponseEntity.ok(Map.of(
                "status", 200,
                "message", "Profile created successfully :)",
                "profile", created
        ));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getProfileByUserId(@PathVariable Long userId) {
        Optional<Profiles> profile = profilesService.getProfileByUserId(userId);
        if (profile.isPresent()) {
            return ResponseEntity.ok(Map.of(
                    "status", 200,
                    "profile", profile.get()
            ));
        } else {
            return ResponseEntity.status(404).body(Map.of(
                    "status", 404,
                    "message", "Profile not found"
            ));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProfile(@PathVariable Long id, @RequestBody Profiles profile) {
        profile.setId(id);
        Profiles updated = profilesService.updateProfile(profile);
        return ResponseEntity.ok(Map.of(
                "status", 200,
                "message", "Profile updated successfully",
                "profile", updated
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProfile(@PathVariable Long id) {
        profilesService.deleteProfile(id);
        return ResponseEntity.ok(Map.of(
                "status", 200,
                "message", "Profile deleted successfully"
        ));
    }
}
