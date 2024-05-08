package com.example.LearningCenter.repository;

import com.example.LearningCenter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByGroup_id(Long groupId);
}
