package com.example.LearningCenter.controller;

import com.example.LearningCenter.entity.Attachment;
import com.example.LearningCenter.service.impl.AttachmentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileUrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/api/auth/attachment")
@RequiredArgsConstructor
public class AttachmentController {
    private final AttachmentServiceImpl attachmentService;

    @PostMapping("/save")
    public ResponseEntity<?> saveFile(@RequestParam(name = "file") MultipartFile multipartFile) {
        return ResponseEntity.ok(attachmentService.saveFile(multipartFile));
    }

    @GetMapping("/download/{hashId}")
    public ResponseEntity<?> download(@PathVariable String hashId) throws MalformedURLException {
        Attachment attachment = attachmentService.findByHashId(hashId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=" + URLEncoder.encode(attachment.getName()))
                .contentType(MediaType.parseMediaType(attachment.getContentType()))
                .body(new FileUrlResource(String.format("%s/%s.%s", attachment.getUploadPath(), attachment.getHashId(), attachment.getExtension())));
    }


    @GetMapping("/{hashId}")
    public ResponseEntity<?> preview(@PathVariable String hashId) throws MalformedURLException {
        Attachment attachment = attachmentService.findByHashId(hashId);
        return ResponseEntity.ok()
                .header(HttpHeaders.EXPIRES, "inline; fileName=" + URLEncoder.encode(attachment.getName()))
                .contentType(MediaType.parseMediaType(attachment.getContentType()))
                .body(new FileUrlResource(String.format("%s/%s.%s",
                        attachment.getUploadPath(),
                        attachment.getHashId(),
                        attachment.getExtension())));
    }

}
