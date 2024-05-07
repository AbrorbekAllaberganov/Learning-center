package com.example.LearningCenter.controller;

import com.example.LearningCenter.payload.GroupPayload;
import com.example.LearningCenter.payload.Result;
import com.example.LearningCenter.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;
    
    @PostMapping
    public ResponseEntity<Result> saveGroup(@RequestBody GroupPayload groupPayload) {
        Result result = groupService.saveGroup(groupPayload);
        return ResponseEntity.status(result.isStatus() ? 200 : 409).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> updateGroup(@PathVariable Long id,
                                              @RequestBody GroupPayload groupPayload) {
        Result result = groupService.updateGroup(id, groupPayload);
        return ResponseEntity.status(result.isStatus() ? 200 : 409).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteGroup(@PathVariable Long id) {
        Result result = groupService.deleteGroup(id);
        return ResponseEntity.status(result.isStatus() ? 200 : 409).body(result);
    }

    @GetMapping
    public ResponseEntity<Result> getAllGroups() {
        Result result = groupService.getGroupsList();
        return ResponseEntity.status(result.isStatus() ? 200 : 409).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> getGroupById(@PathVariable Long id) {
        Result result = groupService.getGroupById(id);
        return ResponseEntity.status(result.isStatus() ? 200 : 409).body(result);
    }

    @GetMapping("/type")
    public ResponseEntity<Result> getTypesOfGroup() {
        Result result = groupService.getTypeOfGroup();
        return ResponseEntity.status(result.isStatus() ? 200 : 409).body(result);
    }

    @GetMapping("/days")
    public ResponseEntity<Result> getDaysOfGroup() {
        Result result = groupService.getDayOfWeek();
        return ResponseEntity.status(result.isStatus() ? 200 : 409).body(result);
    }

}
