# CRUD 서버 참고할 것들

## 사전 요구 사항
- Java 11 이상
- Spring Boot 2.5 이상
- MinIO 서버
- PostgreSQL 데이터베이스
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

3. `src/main/resources/application.yml` 또는 `src/main/resources/application.properties` 파일을 수정하여 다음 설정을 추가한다.

### application.properties

    ```properties
    spring.application.name=PacePitch-server
    server.port=8080
    springdoc.api-docs.enabled=true

    # Minio
    minio.endpoint=http://localhost:9000
    minio.access-key=admin
    minio.secret-key=changeme
    minio.bucket-name=buckettest

    spring.servlet.multipart.max-file-size=100MB
    spring.servlet.multipart.max-request-size=100MB

    # PostgreSQL DB
    spring.datasource.url=jdbc:postgresql://localhost:5432/pacepitch_test
    spring.datasource.username=pacepitch
    spring.datasource.password=1234

    # Hibernate
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
    ```

4. PostgreSQL 데이터베이스 설정
   - PostgreSQL이 설치되어 있어야 한다.
   - 데이터베이스와 유저를 생성한다.
     ```sql
     CREATE DATABASE pacepitch_test;
     CREATE USER pacepitch WITH PASSWORD '1234';
     GRANT ALL PRIVILEGES ON DATABASE pacepitch_test TO pacepitch;
     ```

5. MinIO 서버 설정
   - MinIO 서버가 실행 중이어야 한다. 
   - 설치되지 않은 경우 docker-compose로 설치.
   - [EXTRA.md](./EXTRA.md) 파일을 참고하여 Docker Compose를 통해 설치
   - docker-compose 관련 내용은 EXTRA.md 참고.
   - MinIO 서버를 실행.

6. 애플리케이션 실행.
    ```bash
    ./gradlew bootRun
    ```
   
## FFmpeg 설치
FFmpeg는 비디오 썸네일 생성을 위해 필요하다.

### MacOS
```bash
brew install ffmpeg
