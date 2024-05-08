package com.example.LearningCenter.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendancePayload {
    Long studentId;
    Long date;
    boolean present;
}
