package com.example.LearningCenter.service;


import com.example.LearningCenter.payload.Result;
import com.example.LearningCenter.payload.TeacherPayload;

public interface TeacherService {
    Result saveTeacher(TeacherPayload teacherPayload);
    Result updateTeacher(Long id,TeacherPayload teacherPayload);
    Result deleteTeacher(Long id);
    Result getTeachersList();

}
