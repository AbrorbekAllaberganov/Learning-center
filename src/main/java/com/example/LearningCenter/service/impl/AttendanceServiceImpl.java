package com.example.LearningCenter.service.impl;

import com.example.LearningCenter.entity.Attendance;
import com.example.LearningCenter.exceptions.ResourceNotFound;
import com.example.LearningCenter.payload.AttendancePayload;
import com.example.LearningCenter.payload.AttendsDTO;
import com.example.LearningCenter.payload.Result;
import com.example.LearningCenter.repository.AttendanceRepository;
import com.example.LearningCenter.repository.UserRepository;
import com.example.LearningCenter.service.AttachmentService;
import com.example.LearningCenter.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;

    @Override
    public Result recordAttendance(AttendancePayload attendancePayload) {
        try{
            Attendance attendance=new Attendance();
            attendance.setDate(new Date(attendancePayload.getDate()));
            attendance.setUser(userRepository.findById(attendancePayload.getStudentId()).orElseThrow(
                    ()->new ResourceNotFound("user","id",attendancePayload.getStudentId())
            ));
            attendance.setPresent(attendancePayload.isPresent());
            attendanceRepository.save(attendance);
            return Result.success(attendance);
        }catch(Exception e){
            return Result.exception(e);
        }
    }

    @Override
    public Result getAttendanceByUserId(AttendsDTO attendsDTO) {
        return Result.success(attendanceRepository.findByStudentIdAndDateBetween(
                attendsDTO.getStudentId(),new Date(attendsDTO.getFromDate()),new Date(attendsDTO.getToDate()))
        );
    }

    @Override
    public Result getAttendanceByGroupIdAndDate(AttendsDTO attendsDTO) {
        return Result.success(attendanceRepository.findByUserGroup_IdAndDateBetween(
                attendsDTO.getGroupId(),new Date(attendsDTO.getFromDate()),new Date(attendsDTO.getToDate()))
        );
    }
}
