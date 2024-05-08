package com.example.LearningCenter.controller;

import com.example.LearningCenter.payload.Result;
import com.example.LearningCenter.payload.UserPayload;
import com.example.LearningCenter.payload.UserPayload;
import com.example.LearningCenter.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

//    @GetMapping
//    public ResponseEntity<Result> getMe(){
//        Result result= userService.getMe();
//        return ResponseEntity.status(result.isStatus()?200:409).body(result);
//    }

    @PostMapping
    public ResponseEntity<Result> registerUser(@RequestBody UserPayload userPayload) {
        Result result=userService.saveUser(userPayload);
        return ResponseEntity.status(result.isStatus() ? 200 : 409).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> updateUser(@PathVariable Long id,
                                                @RequestBody UserPayload teacherPayload){
        Result result=userService.updateUser(id,teacherPayload);
        return ResponseEntity.status(result.isStatus() ? 200 : 409).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteUser(@PathVariable Long id){
        Result result=userService.deleteUser(id);
        return ResponseEntity.status(result.isStatus() ? 200 : 409).body(result);
    }

    @GetMapping
    public ResponseEntity<Result> getUser(){
        Result result=userService.getUsersList();
        return ResponseEntity.status(result.isStatus() ? 200 : 409).body(result);
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<Result> getUserById(@PathVariable Long groupId){
        Result result=userService.getUsersByGroupId(groupId);
        return ResponseEntity.status(result.isStatus() ? 200 : 409).body(result);
    }

}
