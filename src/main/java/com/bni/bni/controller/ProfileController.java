package com.bni.bni.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import com.bni.bni.service.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private ProfileService authService;

    @PostMapping("/add-profile")
    public ResponseEntity<Map<String, Object>> addProfile(@RequestBody Map<String, String> body) {
        Long user_id = Long.parseLong(body.get("user_id"));
        String first_name = body.get("first_name");
        String last_name = body.get("last_name");
        String place_of_birth = body.get("place_of_birth");
        LocalDate date_of_birth = LocalDate.parse(body.get("date_of_birth"));

        String message = authService.addProfile(user_id, first_name, last_name, place_of_birth, date_of_birth);
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", 200);
        response.put("message", message);

        return ResponseEntity.ok(response);
    }
}