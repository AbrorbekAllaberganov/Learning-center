package com.example.LearningCenter.service;

import com.example.LearningCenter.payload.AttendancePayload;
import com.example.LearningCenter.payload.AttendsDTO;
import com.example.LearningCenter.payload.Result;

import java.util.Date;

public interface AttendanceService {
    Result recordAttendance(AttendancePayload attendancePayload);

    Result getAttendanceByUserId(AttendsDTO attendsDTO);

    Result getAttendanceByGroupIdAndDate(AttendsDTO attendsDTO);

}
