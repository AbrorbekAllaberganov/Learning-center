package com.example.LearningCenter.service;

import com.example.LearningCenter.entity.User;
import com.example.LearningCenter.payload.Result;
import com.example.LearningCenter.payload.UserPayload;


public interface UserService {
    Result saveUser(UserPayload userPayload);
    Result updateUser(Long userId, UserPayload userPayload);
    Result deleteUser(Long userId);
    Result getUsersList();

    Result getMe();
    User findUserById(Long id);
}
