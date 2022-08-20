FROM gradle:7.5.1-jdk11-alpine
MAINTAINER makhee <fzl7808777@gmail.com>

COPY . /home/category_be
WORKDIR /home/category_be

RUN gradle clean build --no-daemon
CMD java -jar build/libs/*.jar