package com.example.LearningCenter.service.impl;

import com.example.LearningCenter.entity.Parent;
import com.example.LearningCenter.exceptions.BadRequest;
import com.example.LearningCenter.exceptions.UnAuthorized;
import com.example.LearningCenter.payload.LoginPayload;
import com.example.LearningCenter.payload.Result;
import com.example.LearningCenter.repository.ParentRepository;
import com.example.LearningCenter.security.JwtTokenProvider;
import com.example.LearningCenter.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final ParentRepository parentRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Result loginUser(LoginPayload loginPayload) {
        Parent parent = parentRepository.findByPhoneNumber(loginPayload.getPhoneNumber());
        if (parent==null){
            return Result.exception(new UnAuthorized("username or password is incorrect"));
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginPayload.getPhoneNumber(), loginPayload.getPassword()));
        String token = jwtTokenProvider.createToken(parent.getPhoneNumber(), parent.getRoles());

        if (token == null) {
            return Result.exception(new BadRequest("Token is not created"));
        }

        Map<String, Object> login = new HashMap<>();
        login.put("token", token);
        login.put("username", parent.getPhoneNumber());
        login.put("Roles", parent.getRoles());
        login.put("success", true);
        return Result.success(login);
    }




}
