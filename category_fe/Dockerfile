FROM nginx:1.21.1-alpine
MAINTAINER makhee <fzl7808777@gmail.com>

RUN apk add nodejs npm
COPY ./conf/nginx.conf /etc/nginx/
COPY . /home/category_fe
WORKDIR /home/category_fe
RUN npm install
RUN npm run build