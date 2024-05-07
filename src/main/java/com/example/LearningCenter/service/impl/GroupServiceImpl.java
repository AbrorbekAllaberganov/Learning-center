package com.example.LearningCenter.service.impl;

import com.example.LearningCenter.entity.DayOfWeek;
import com.example.LearningCenter.entity.Group;
import com.example.LearningCenter.entity.TypeOfGroup;
import com.example.LearningCenter.exceptions.ResourceNotFound;
import com.example.LearningCenter.payload.GroupPayload;
import com.example.LearningCenter.payload.Result;
import com.example.LearningCenter.repository.GroupRepository;
import com.example.LearningCenter.repository.TeacherRepository;
import com.example.LearningCenter.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public Result saveGroup(GroupPayload groupPayload) {
        try{
            Group group=new Group();
            group.setName(groupPayload.getName());
            group.setDescription(groupPayload.getDescription());
            group.setTypeOfGroup(getTypeOfGroup(groupPayload.getTypeOfGroup()));
            group.setDayOfWeek(getDayOfWeek(groupPayload.getDayOfWeek()));
            group.setTeacher(teacherRepository.findById(groupPayload.getTeacherId()).orElseThrow(
                    ()->new ResourceNotFound("teacher","id",groupPayload.getTeacherId())
            ));

            groupRepository.save(group);
            return Result.success(group);
        }catch(Exception e){
            return Result.exception(e);
        }
    }

    @Override
    public Result updateGroup(Long id, GroupPayload groupPayload) {
        try{
            Group group=groupRepository.findById(id).orElseThrow(
                    ()->new ResourceNotFound("group","id",id)
            );
            group.setName(groupPayload.getName());
            group.setDescription(groupPayload.getDescription());
            group.setTypeOfGroup(getTypeOfGroup(groupPayload.getTypeOfGroup()));
            group.setDayOfWeek(getDayOfWeek(groupPayload.getDayOfWeek()));
            group.setTeacher(teacherRepository.findById(groupPayload.getTeacherId()).orElseThrow(
                    ()->new ResourceNotFound("teacher","id",groupPayload.getTeacherId())
            ));

            groupRepository.save(group);
            return Result.success(group);
        }catch(Exception e){
            return Result.exception(e);
        }
    }

    @Override
    public Result deleteGroup(Long id) {
        try{
            groupRepository.deleteById(id);
            return Result.message("Group has been deleted",true);
        }catch(Exception e){
            return Result.exception(e);
        }
    }

    @Override
    public Result getGroupsList() {
        return Result.success(groupRepository.findAll(Sort.by("id")));
    }

    @Override
    public Result getGroupById(Long id) {
        return Result.success(groupRepository.findById(id).orElseThrow(
                ()->new ResourceNotFound("group","id",id)
        ));
    }

    @Override
    public Result getTypeOfGroup() {
        return Result.success(TypeOfGroup.values());
    }

    @Override
    public Result getDayOfWeek() {
        return Result.success(DayOfWeek.values());
    }

    public TypeOfGroup getTypeOfGroup(String type){
        if ("INDIVIDUAL".equals(type)) {
            return TypeOfGroup.INDIVIDUAL;
        }
        return TypeOfGroup.STANDARD;
    }

    public DayOfWeek getDayOfWeek(String dayOfWeek){
        return switch (dayOfWeek) {
            case "ODD" -> DayOfWeek.ODD;
            case "EVEN" -> DayOfWeek.EVEN;
            default -> DayOfWeek.FULL;
        };
    }
}
