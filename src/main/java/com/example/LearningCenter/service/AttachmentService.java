package com.example.LearningCenter.service;

import org.springframework.web.multipart.MultipartFile;

public interface AttachmentService {
    String saveFile(MultipartFile multipartFile);

}
