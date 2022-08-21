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

## Installation (Centos 7기준)
### 패키지 업데이트
```
$ sudo yum -y update
```

### Git 설치 && Clone
```
$ sudo yum install git

$ git clone https://github.com/makhee/category-service.git
```

### Docker 설치
[참고 사이트](https://velog.io/@thipu/Centos-7-docker-docker-compose-%EC%84%A4%EC%B9%98)

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

# docker 버전 확인
$ sudo docker --version
Docker version 20.10.17, build 100c701
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
#### permission denied 발생하는 경우
```
# permission denied 발생하는 경우 아래 커맨드 입력
$ sudo groupadd docker
$ sudo usermod -aG docker $USER
$ sudo chmod 666 /var/run/docker.sock
```

## Connect
docker 가 정상적으로 UP 된다면 아래 URL 로 접근가능합니다.

frontend: http://localhost

backend: http://localhost:8080
