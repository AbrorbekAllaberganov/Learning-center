package com.example.LearningCenter.repository;

import com.example.LearningCenter.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    Attachment findByHashId(String hashId);
    boolean deleteByHashId(String hashId);
}
