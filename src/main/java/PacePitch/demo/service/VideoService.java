package PacePitch.demo.service;

import PacePitch.demo.model.IndividualPitchEntity;
import PacePitch.demo.model.PitchingSessionEntity;
import PacePitch.demo.repository.IndividualPitchRepository;
import PacePitch.demo.repository.PitchingSessionRepository;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import java.io.File;
import java.nio.file.Files;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;

@Service
public class VideoService {

    private final MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String bucketName;

    @Autowired
    private IndividualPitchRepository individualPitchRepository;
    @Autowired
    private PitchingSessionRepository pitchingSessionRepository;
    public VideoService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    // 비디오 파일을 업로드하고 MinIO 객체 이름을 데이터베이스에 저장하는 메서드
    public String uploadVideo(UUID sessionId) throws IOException, MinioException, NoSuchAlgorithmException, InvalidKeyException {
        Optional<PitchingSessionEntity> sessionOpt = pitchingSessionRepository.findById(sessionId);
        if (!sessionOpt.isPresent()) {
            throw new IllegalArgumentException("No session found with ID: " + sessionId);
        }
        PitchingSessionEntity session = sessionOpt.get();

        String presignedUrl = minioClient.getPresignedObjectUrl(
            GetPresignedObjectUrlArgs.builder()
                        .method(Method.PUT)
                        .bucket(bucketName)
                        .object(UUID.randomUUID().toString())
                        .expiry(604800, TimeUnit.SECONDS) // 7일 유효 기간
                        .build()
        );

        return presignedUrl;
    }

    // id를 통한 단일 비디오 조회 메서드
    public String getVideoUrl(UUID id) {
        IndividualPitchEntity video = getVideo(id);
        return video != null ? video.getVideoUrl() : null;
    }

    // id를 통한 썸네일 URL 조회 메서드
    public String getVideoThumb(UUID id) {
        IndividualPitchEntity video = getVideo(id);
        return (video != null && video.getThumbnailUrl() != null && !video.getThumbnailUrl().isEmpty()) ? video.getThumbnailUrl() : null;
    }

    // id를 통한 메모 조회 메서드
    public String getMemo(UUID id) {
        IndividualPitchEntity video = getVideo(id);
        return (video != null && video.getMemo() != null && !video.getMemo().isEmpty()) ? video.getMemo() : null;
    }


    private IndividualPitchEntity getVideo(UUID id) {
        return individualPitchRepository.findById(id).orElse(null);
    }

    // 세션에 대한 대표 썸네일과 제목 조회
    public Object[] getOldestThumbnailUrlAndSessionTitle(UUID sessionId) {
        return individualPitchRepository.findOldestThumbnailUrlAndSessionTitle(sessionId);
    }
}