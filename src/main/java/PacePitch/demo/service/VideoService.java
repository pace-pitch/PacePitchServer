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

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;

import javax.imageio.ImageIO;

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

        // 썸네일 이미지 생성
        File videoFile = new File(System.getProperty("java.io.tmpdir") + "/" + filename);
        file.transferTo(videoFile);

        String thumbnailFilename = "thumbnail_" + filename + ".png";
        File thumbnailFile = new File(System.getProperty("java.io.tmpdir") + "/" + thumbnailFilename);

        try {
            generateThumbnail(videoFile, thumbnailFile);
        } catch (Exception e) {
            throw new IOException("Failed to generate thumbnail: " + e.getMessage(), e);
        }

        // 썸네일 이미지 업로드
        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(thumbnailFilename)
                            .stream(Files.newInputStream(thumbnailFile.toPath()), Files.size(thumbnailFile.toPath()), -1)
                            .contentType("image/png")
                            .build()
            );
        } catch (Exception e) {
            throw new IOException("Failed to upload thumbnail: " + e.getMessage(), e);
        }

        // Presigned URL 생성
        String presignedUrl = minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(bucketName)
                        .object(filename)
                        .expiry(604800, TimeUnit.SECONDS) // 7일 유효 기간
                        .build()
        );
        String thumbnailUrl = minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(bucketName)
                        .object(thumbnailFilename)
                        .expiry(604800, TimeUnit.SECONDS) // 7일 유효 기간
                        .build()
        );

        // IndividualPitchEntity 생성 및 업데이트
        IndividualPitchEntity pitch = new IndividualPitchEntity();
        pitch.setSession(new PitchingSessionEntity(sessionId));
        pitch.setMinioUrl(presignedUrl);
        pitch.setThumbnailUrl(thumbnailUrl);
        repository.save(pitch);
    }

    // id를 통한 단일 비디오 조회 메서드
    public String getVideoUrl(UUID id) {
        IndividualPitchEntity video = getVideo(id);
        return video != null ? video.getMinioUrl() : null;
    }
    private IndividualPitchEntity getVideo(UUID id) {
        return repository.findById(id).orElse(null);
    }

    private void generateThumbnail(File videoFile, File thumbnailFile) throws IOException {
        FFmpeg ffmpeg = new FFmpeg("/opt/homebrew/bin/ffmpeg"); // ffmpeg 실행 파일 경로
        FFprobe ffprobe = new FFprobe("/opt/homebrew/bin/ffprobe"); // ffprobe 실행 파일 경로

        FFmpegProbeResult probeResult = ffprobe.probe(videoFile.getAbsolutePath());

        FFmpegBuilder builder = new FFmpegBuilder()
                .setInput(probeResult)
                .addOutput(thumbnailFile.getAbsolutePath())
                .setFrames(1)
                .setVideoFilter("select='gte(n\\,10)'")
                .done();

        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
        executor.createJob(builder).run();
    }
}
