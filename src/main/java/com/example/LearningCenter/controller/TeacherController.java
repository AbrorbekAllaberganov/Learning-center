package com.example.LearningCenter.controller;

import com.example.LearningCenter.payload.Result;
import com.example.LearningCenter.payload.TeacherPayload;
import com.example.LearningCenter.service.impl.TeacherServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherServiceImpl teacherService;

    @PostMapping
    public ResponseEntity<Result> saveTeacher(@RequestBody TeacherPayload teacherPayload){
        Result result=teacherService.saveTeacher(teacherPayload);
        return ResponseEntity.status(result.isStatus() ? 200 : 409).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> updateTeacher(@PathVariable Long id,
                                                @RequestBody TeacherPayload teacherPayload){
        Result result=teacherService.updateTeacher(id,teacherPayload);
        return ResponseEntity.status(result.isStatus() ? 200 : 409).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteTeacher(@PathVariable Long id){
        Result result=teacherService.deleteTeacher(id);
        return ResponseEntity.status(result.isStatus() ? 200 : 409).body(result);
    }

    @GetMapping
    public ResponseEntity<Result> getTeacher(){
        Result result=teacherService.getTeachersList();
        return ResponseEntity.status(result.isStatus() ? 200 : 409).body(result);
    }

}

