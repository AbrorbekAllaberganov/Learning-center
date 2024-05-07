package com.example.LearningCenter.service;


import com.example.LearningCenter.payload.GroupPayload;
import com.example.LearningCenter.payload.Result;

public interface GroupService {
    Result saveGroup(GroupPayload groupPayload);
    Result updateGroup(Long id, GroupPayload groupPayload);
    Result deleteGroup(Long id);
    Result getGroupsList();
    Result getGroupById(Long id);
    Result getTypeOfGroup();
    Result getDayOfWeek();
}
