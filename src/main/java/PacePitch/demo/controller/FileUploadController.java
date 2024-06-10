package PacePitch.demo.controller;

import PacePitch.demo.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/session")
@CrossOrigin(origins = "*")
public class FileUploadController {

    @Autowired
    private VideoService videoService;

    // 비디오 파일 업로드 엔드포인트
    @PostMapping("/{sessionId}/upload/video")
    public ResponseEntity<String> uploadFile(@PathVariable("sessionId") UUID sessionId, @RequestParam("file") MultipartFile file) {
        try {
            videoService.uploadVideo(file, sessionId);
            return ResponseEntity.ok("File uploaded succeessfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
        }
    }

    // 특정 세션에 대한 대표 썸네일과 제목 조회
    @GetMapping("/{sessionId}/upload/thumb")
    public ResponseEntity<Object[]> getOldestThumbnailAndTitle(@PathVariable("sessionId") UUID sessionId) {
        Object[] result = videoService.getOldestThumbnailUrlAndSessionTitle(sessionId);
        return ResponseEntity.ok(result);
    }

    // 특정 비디오 파일 조회 엔드포인트
    @GetMapping("/upload/video/{videoId}")
    public ResponseEntity<String> getVideo(@PathVariable("videoId") UUID videoId) {

        String videoUrl = videoService.getVideoUrl(videoId);
        return ResponseEntity.ok(videoUrl);
    }

    // 하나의 비디오 파일에 대한 썸네일 생성 엔드포인트
    @GetMapping("/upload/thumb/{videoId}")
    public  ResponseEntity<Map<String, String>> getVideoThumbAndMemo(@PathVariable("videoId") UUID videoId) {
        try {
            String thumbnailUrl = videoService.getVideoThumb(videoId);
            String memo = videoService.getMemo(videoId);

            Map<String, String> response = new HashMap<>();
            response.put("thumbnailUrl", thumbnailUrl);
            response.put("memo", memo);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

}
