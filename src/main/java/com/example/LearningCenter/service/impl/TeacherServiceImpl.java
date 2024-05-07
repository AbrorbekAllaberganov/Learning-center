package com.example.LearningCenter.service.impl;

import com.example.LearningCenter.entity.Parent;
import com.example.LearningCenter.entity.Teacher;
import com.example.LearningCenter.entity.User;
import com.example.LearningCenter.exceptions.ResourceNotFound;
import com.example.LearningCenter.payload.Result;
import com.example.LearningCenter.payload.TeacherPayload;
import com.example.LearningCenter.repository.ParentRepository;
import com.example.LearningCenter.repository.RoleRepository;
import com.example.LearningCenter.repository.TeacherRepository;
import com.example.LearningCenter.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final AttachmentServiceImpl attachmentService;
    private final PasswordEncoder passwordEncoder;
    private final ParentRepository parentRepository;
    private final RoleRepository roleRepository;


    @Override
    public Result saveTeacher(TeacherPayload teacherPayload) {
        try {
            Parent parent = new Parent();
            parent.setPhoneNumber(teacherPayload.getPhoneNumber());
            parent.setPassword(passwordEncoder.encode(teacherPayload.getPassword()));
            parent.setRoles(Collections.singletonList(roleRepository.findByName("ROLE_TEACHER")));

            parentRepository.save(parent);


            Teacher teacher = new Teacher();
            teacher.setParent(parent);
            teacher.setFirstName(teacherPayload.getFirstName());
            teacher.setLastName(teacherPayload.getLastName());
            teacher.setEmail(teacherPayload.getEmail());
            teacher.setAddress(teacherPayload.getAddress());
            teacher.setGender(teacherPayload.getGender());
            teacher.setFile(attachmentService.findByHashId(teacherPayload.getHashId()));
            teacher.setDateOfBirth(new Date(teacherPayload.getDateOfBirth()));

            teacherRepository.save(teacher);

            return Result.success(teacher);
        } catch (Exception e) {
            return Result.exception(e);
        }
    }

    @Override
    public Result updateTeacher(Long id, TeacherPayload teacherPayload) {
        try {
            Teacher teacher = teacherRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFound("teacher", "id", id)
            );

            Parent parent = new Parent();
            parent.setPhoneNumber(teacherPayload.getPhoneNumber());
            parent.setPassword(passwordEncoder.encode(teacherPayload.getPassword()));

            parentRepository.save(parent);


            teacher.setParent(parent);
            teacher.setFirstName(teacherPayload.getFirstName());
            teacher.setLastName(teacherPayload.getLastName());
            teacher.setEmail(teacherPayload.getEmail());
            teacher.setAddress(teacherPayload.getAddress());
            teacher.setGender(teacherPayload.getGender());
            teacher.setFile(attachmentService.findByHashId(teacherPayload.getHashId()));
            teacher.setDateOfBirth(new Date(teacherPayload.getDateOfBirth()));

            teacherRepository.save(teacher);

            return Result.success(teacher);
        } catch (Exception e) {
            return Result.exception(e);
        }
    }

    @Override
    public Result deleteTeacher(Long id) {
        try {
            teacherRepository.deleteById(id);
            return Result.message("Teacher has been deleted", true);
        }catch(Exception e) {
            return Result.exception(e);
        }
    }

    @Override
    public Result getTeachersList() {
        return Result.success(teacherRepository.findAll(Sort.by("id")));
    }
}
