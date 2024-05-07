package com.example.LearningCenter.payload;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(description = "Login details")
public class LoginPayload {
    private String phoneNumber;
    private String password;
}
