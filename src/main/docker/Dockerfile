FROM java:8
MAINTAINER jrrall@gmail.com
VOLUME /tmp
ADD vtube*.jar app.jar
EXPOSE 8080
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]