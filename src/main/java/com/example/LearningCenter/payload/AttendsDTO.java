package com.example.LearningCenter.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttendsDTO {
    Long studentId;
    Long groupId;
    Long fromDate;
    Long toDate;

}
