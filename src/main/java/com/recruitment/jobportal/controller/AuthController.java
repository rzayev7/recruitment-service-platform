package com.recruitment.jobportal.controller;

import com.recruitment.jobportal.dto.LoginRequest;
import com.recruitment.jobportal.entity.User;
import com.recruitment.jobportal.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void register(@RequestBody User user){
        userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
        userService.login(loginRequest);
        return "Login endpoint";
    }
}
