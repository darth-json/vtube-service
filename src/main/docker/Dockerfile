FROM openjdk:8-jre-alpine

MAINTAINER jrrall@gmail.com
ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    STARTUP_DELAY=0

VOLUME /tmp

ADD *.jar app.jar
EXPOSE 8080

CMD echo "The application will start in ${STARTUP_DELAY}s..." && \
    sleep ${STARTUP_DELAY} && \
    java -Dspring.profiles.active=prod -Djava.security.egd=file:/dev/./urandom -jar /app.jar