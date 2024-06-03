# CRUD 서버

## 사전 요구 사항
- Java 11 이상
- Spring Boot 2.5 이상
- MinIO 서버
- FFmpeg 설치 (비디오 썸네일 생성을 위해 필요)

## 설치 및 실행
1. 이 프로젝트를 클론한다.
    ```bash
    git clone https://github.com/your-repo/video-upload-api.git
    cd video-upload-api
    ```

2. 필요한 의존성을 설치한다.
    ```bash
    ./gradlew build
    ```

3. `application.yml` 또는 `application.properties` 파일을 수정하여 MinIO 설정을 추가한다.
    ```yaml
    minio:
      endpoint: http://localhost:9000
      access-key: admin
      secret-key: changeme
      bucket-name: buckettest
    ```

## FFmpeg 설치
FFmpeg는 비디오 썸네일 생성을 위해 필요하다. Homebrew를 사용하여 설치할 수 있다.

### MacOS
```bash
brew install ffmpeg
