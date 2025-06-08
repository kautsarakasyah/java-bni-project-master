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

    public String register(String username, String emailAddress, String password) {
        if (repo.existsByUsername(username)) {
            return "User already exists";
        }
        if (repo.existsByEmailAddress(emailAddress)) {
            return "Email already exists";
        }

        User user = new User();
        user.setUsername(username);
        user.setEmailAddress(emailAddress);
        user.setPassword(encoder.encode(password));
        user.setIsActive(true);
        user.setCreatedAt(OffsetDateTime.now());
        user.setUpdatedAt(null);
        repo.save(user);

        return "Registered successfully";
    }

    public String login(String username, String password) {
        Optional<User> user = repo.findByUsername(username);
        if (user.isPresent() && encoder.matches(password, user.get().getPassword())) {
            // Token generation, adjust as needed (no role in entity)
            return jwtUtil.generateToken(username, null);
        }
        return null;
    }
}