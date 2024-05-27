package PacePitch.demo.controller;

import PacePitch.demo.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.rmi.server.UID;
import java.util.UUID;

@RestController
@RequestMapping("/session/{sessionId}/upload")
public class FileUploadController {

    @Autowired
    private VideoService videoService;

    // 비디오 파일 업로드 엔드포인트
    @PostMapping("/video")
    public ResponseEntity<String> uploadFile(@PathVariable("sessionId") UUID sessionId, @RequestParam("file") MultipartFile file) {
        try {
            videoService.uploadVideo(file, sessionId);
            return ResponseEntity.ok("File uploaded succeessfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
        }
    }
}
