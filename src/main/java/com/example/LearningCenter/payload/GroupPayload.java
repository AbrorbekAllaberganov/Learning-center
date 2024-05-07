package com.example.LearningCenter.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupPayload {
    String name;
    String description;
    String typeOfGroup;
    String dayOfWeek;
    Long teacherId;
}
