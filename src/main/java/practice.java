//package PacePitch.demo.controller;
//
//import PacePitch.demo.service.VideoService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/session/{sessionId}/upload")
//public class FileUploadController {
//
//    @Autowired
//    private VideoService videoService;
//
//    // 비디오 파일 업로드 엔드포인트
//    @PostMapping("/video")
//    public ResponseEntity<String> uploadFile(@PathVariable("sessionId") UUID sessionId, @RequestParam("file") MultipartFile file) {
//        try {
//            videoService.uploadVideo(file, sessionId);
//            return ResponseEntity.ok("File uploaded successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
//        }
//    }
//
//    // 하나의 비디오에 대한 썸네일과 메모 조회 엔드포인트
//    @GetMapping("/thumb/{videoId}")
//    public ResponseEntity<Map<String, String>> getVideoThumbAndMemo(@PathVariable("videoId") UUID videoId) {
//        try {
//            String thumbnailUrl = videoService.getVideoThumbnailUrl(videoId);
//            String memo = videoService.getMemo(videoId);
//
//            Map<String, String> response = new HashMap<>();
//            response.put("thumbnailUrl", thumbnailUrl);
//            response.put("memo", memo);
//
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body(null);
//        }
//    }
//}
