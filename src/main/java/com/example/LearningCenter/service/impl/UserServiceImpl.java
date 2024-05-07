package com.example.LearningCenter.service.impl;

import com.example.LearningCenter.entity.Parent;
import com.example.LearningCenter.entity.User;
import com.example.LearningCenter.exceptions.BadRequest;
import com.example.LearningCenter.exceptions.ResourceNotFound;
import com.example.LearningCenter.payload.Result;
import com.example.LearningCenter.payload.UserPayload;
import com.example.LearningCenter.repository.ParentRepository;
import com.example.LearningCenter.repository.UserRepository;
import com.example.LearningCenter.security.SecurityUtils;
import com.example.LearningCenter.service.AttachmentService;
import com.example.LearningCenter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final SecurityUtils securityUtils;
    private final ParentRepository parentRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AttachmentServiceImpl attachmentService;


    @Override
    public Result saveUser(UserPayload userPayload) {
        try {
            Parent parent = new Parent();
            parent.setPhoneNumber(userPayload.getPhoneNumber());
            parent.setPassword(passwordEncoder.encode(userPayload.getPassword()));

            parentRepository.save(parent);


            User user = new User();
            user.setParent(parent);
            user.setFirstName(userPayload.getFirstName());
            user.setLastName(userPayload.getLastName());
            user.setEmail(userPayload.getEmail());
            user.setAddress(userPayload.getAddress());
            user.setGender(userPayload.getGender());
            user.setFile(attachmentService.findByHashId(userPayload.getHashId()));
            user.setDateOfBirth(LocalDateTime.parse(userPayload.getDate()));

            userRepository.save(user);

            return Result.success(user);
        } catch (Exception e) {
            return Result.exception(e);
        }

    }

    @Override
    public Result updateUser(Long userId, UserPayload userPayload) {
        try {
            User user =userRepository.findById(userId).orElseThrow(
                    ()->new ResourceNotFound("user","id",userId)
            );
            Parent parent = user.getParent();
            parent.setPhoneNumber(userPayload.getPhoneNumber());
            parent.setPassword(passwordEncoder.encode(userPayload.getPassword()));

            parentRepository.save(parent);


            user.setParent(parent);
            user.setFirstName(userPayload.getFirstName());
            user.setLastName(userPayload.getLastName());
            user.setEmail(userPayload.getEmail());
            user.setAddress(userPayload.getAddress());
            user.setGender(userPayload.getGender());
            user.setFile(attachmentService.findByHashId(userPayload.getHashId()));
            user.setDateOfBirth(LocalDateTime.parse(userPayload.getDate()));

            userRepository.save(user);

            return Result.success(user);
        } catch (Exception e) {
            return Result.exception(e);
        }
    }

    @Override
    public Result deleteUser(Long userId) {
        try{
            userRepository.deleteById(userId);
            return Result.message("user has been deleted", true);
        }catch(Exception e){
            return Result.exception(e);
        }
    }

    @Override
    public Result getUsersList() {
        return Result.success(userRepository.findAll(Sort.by("id")));
    }

    @Override
    public Result getMe() {
        try {
            Optional<String> currentUser = securityUtils.getCurrentUser();
            if (currentUser.isEmpty())
                throw new BadRequest("User not found");
            return Result.success(parentRepository.findByPhoneNumber(currentUser.get()));
        } catch (Exception e) {
            return Result.exception(e);
        }
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("user", "id", id)
        );
    }
}
