# MinIO 설정 가이드

## Docker & Docker Compose 설치

먼저 Docker와 Docker Compose를 설치
```bash
brew install --cask docker
brew install docker-compose
```
Docker Compose 설정
원하는 디렉토리에서 docker-compose.yml 파일을 작성한다.
예) minio라는 디렉토리를 만들고 그 안에 docker-compose.yml 파일을 작성한다.
```bash
mkdir minio
cd minio
nano docker-compose.yml
```
### docker-compose.yml 파일에 작성
```yaml
version: '3'
services:
  s3:
    image: minio/minio
    ports:
      - 9000:9000
      - 9001:9001
    environment:
      MINIO_ROOT_USER: admin
      MINIO_ROOT_PASSWORD: changeme
    volumes:
      - ./storage/minio:/data
    command: server /data --console-address ":9001"
```

### MinIO 서비스 시작
docker-compose.yml 파일이 있는 디렉토리에서 다음 명령어를 사용하여 서비스를 시작한다.
```bash
docker-compose up -d
```
### MinIO 웹 콘솔 접속
Docker 컨테이너가 정상적으로 실행되고 나면, 웹 브라우저를 열어 다음 주소로 접속.

http://localhost:9000

MINIO_ROOT_USER와 MINIO_ROOT_PASSWORD를 사용하여 로그인한다.