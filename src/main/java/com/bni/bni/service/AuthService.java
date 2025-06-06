package com.bni.bni.service;

import com.bni.bni.entity.User;
import com.bni.bni.repository.UserRepository;
import com.bni.bni.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String register(String username, String password_hash, String email_address) {
        if (username == null || username.isBlank() ||
            password_hash == null || password_hash.isBlank() ||
            email_address == null || email_address.isBlank()) {
            return "Username, password, dan email tidak boleh kosong";
        }

        if (repo.existsByUsername(username)) {
            return "User already exists";
        }

        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(encoder.encode(password_hash));
        user.setRole("USER");
        user.setCreatedAt(OffsetDateTime.now());
        user.setUpdateAt(OffsetDateTime.now());
        user.setIsActive(true);
        user.setEmailAddress(email_address);

        repo.save(user);
        return "Registered successfully";
    }

    public String login(String username, String password_hash) {
        Optional<User> user = repo.findByUsername(username);
        if (user.isPresent() && encoder.matches(password_hash, user.get().getPasswordHash())) {
            return jwtUtil.generateToken(username, user.get().getRole());
        }
        return null;
    }
}