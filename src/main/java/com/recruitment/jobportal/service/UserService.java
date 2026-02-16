package com.recruitment.jobportal.service;

import com.recruitment.jobportal.dto.LoginRequest;
import com.recruitment.jobportal.dto.RegisterRequest;
import com.recruitment.jobportal.entity.Role;
import com.recruitment.jobportal.entity.User;
import com.recruitment.jobportal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // REGISTER
    public User register(RegisterRequest registerRequest) {

        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }
        User user = new User();
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(Role.valueOf(registerRequest.getRole().toUpperCase()));

        return userRepository.save(user);
    }

    public String login(LoginRequest loginRequest) {

        User dbUser = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), dbUser.getPassword())) {
            throw new RuntimeException("Incorrect password");
        }

        return "Logged in successfully";
    }
}
