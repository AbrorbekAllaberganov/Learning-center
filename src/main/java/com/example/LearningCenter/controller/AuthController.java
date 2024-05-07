package com.example.LearningCenter.controller;

import com.example.LearningCenter.payload.LoginPayload;
import com.example.LearningCenter.payload.Result;
import com.example.LearningCenter.service.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthServiceImpl authService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginPayload loginPayload) {
        Result result = authService.loginUser(loginPayload);
        return ResponseEntity.status(result.isStatus() ? 200 : 401).body(result);
    }


}

