package com.example.LearningCenter.payload;

import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(description = "Register details")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeacherPayload {
    String email;
    String phoneNumber;
    String password;
    String firstName;
    String lastName;
    String address;
    Long dateOfBirth;
    String hashId;
    String gender;

}
