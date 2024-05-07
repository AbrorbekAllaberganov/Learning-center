package com.example.LearningCenter.service;

import com.example.LearningCenter.payload.LoginPayload;
import com.example.LearningCenter.payload.Result;

public interface AuthService {
    Result loginUser(LoginPayload loginPayload);
}
