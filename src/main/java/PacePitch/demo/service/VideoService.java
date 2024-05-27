package PacePitch.demo.service;

import PacePitch.demo.model.IndividualPitchEntity;
import PacePitch.demo.model.PitchingSessionEntity;
import PacePitch.demo.repository.IndividualPitchRepository;
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
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class VideoService {

    private final MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String bucketName;

    @Autowired
    private IndividualPitchRepository repository;

    public VideoService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    // 비디오 파일을 업로드하고 MinIO 객체 이름을 데이터베이스에 저장하는 메서드
    public void uploadVideo(MultipartFile file, UUID sessionId) throws IOException, MinioException, NoSuchAlgorithmException, InvalidKeyException {
        String filename = file.getOriginalFilename();

        // 파일 minio에 업로드
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(filename)
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build()
        );

        // Presigned URL 생성
        String presignedUrl = minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(bucketName)
                        .object(filename)
                        .expiry(3600, TimeUnit.SECONDS) // 1시간 유효 시간
                        .build()
        );

        // IndividualPitchEntity 생성 및 업데이트
        IndividualPitchEntity pitch = new IndividualPitchEntity();
        pitch.setSession(new PitchingSessionEntity(sessionId));
        pitch.setMinioUrl(presignedUrl);
        repository.save(pitch);
    }
}
