# Category
Category Service 관련 레포지토리입니다.

## Spec
 - **Backend**
   - Framework
     - Spring Boot
     - 2.7.2
   - Language
     - java
     - 11
   - Compiler
     - Gradle
     - 7.5.1
   - Database
     - H2
 - **Frontend**
   - Framework
      - Vue
      - 2.6.14
   - Language
      - JavaScript

## Installation
### Docker 설치 (Centos 7)
[참고 사이트](https://velog.io/@thipu/Centos-7-docker-docker-compose-%EC%84%A4%EC%B9%98)

#### 패키지 업데이트
```
$ yum -y update
```
#### docker 설치
```
# docker 설치
$ sudo curl -fsSl https://get.docker.com/ |sh
# 또는
$ sudo yum -y docker;

# docker 서비스 시작
$ sudo systemctl start docker

# docker 상태
$ sudo systemctl status docker

# 부팅시 자동 시작 설정
$ sudo systemctl enable docker

# docker
$ sudo usermod -aG docker docker

# docker 버전 확인
$ sudo docker --version
Docker version 1.13.1, build 7d71120/1.13.1 
```

#### docker-compose 설치
```
# docker-compose 설치
$ sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose

# docker-compose 실행 권한 설정
$ sudo chmod +x /usr/local/bin/docker-compose

# docker-compose 버전 확인
$ docker-compose --version
docker-compose version 1.29.2, build 5becea4c
```

## Build
docker-compose 를 수행 하면서 backend 와 frontend 를 빌드합니다.
```
# project dir 이동후 docker-compose 커맨드 실행
$ docker-compose up -d --build --force-recreate
```

## Connect
docker 가 정상적으로 UP 된다면 아래 URL 로 접근가능합니다.

frontend: http://localhost

backend: http://localhost:8080
