package com.example.LearningCenter.controller;

import com.example.LearningCenter.payload.AttendancePayload;
import com.example.LearningCenter.payload.AttendsDTO;
import com.example.LearningCenter.payload.Result;
import com.example.LearningCenter.service.impl.AttendanceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {
    private final AttendanceServiceImpl attendanceService;

    @PostMapping
    public ResponseEntity<Result> recordAttendance(@RequestBody AttendancePayload attendancePayload){
        Result result=attendanceService.recordAttendance(attendancePayload);
        return ResponseEntity.status(result.isStatus() ? 200 : 409).body(result);
    }

    @GetMapping
    public ResponseEntity<Result> getAttendanceByUserId(@RequestBody AttendsDTO attendsDTO){
        Result result=attendanceService.getAttendanceByUserId(attendsDTO);
        return ResponseEntity.status(result.isStatus() ? 200 : 409).body(result);
    }

    @GetMapping("/group")
    public ResponseEntity<Result> getAttendanceByGroupId(@RequestBody AttendsDTO attendsDTO){
        Result result=attendanceService.getAttendanceByGroupIdAndDate(attendsDTO);
        return ResponseEntity.status(result.isStatus() ? 200 : 409).body(result);
    }

}
