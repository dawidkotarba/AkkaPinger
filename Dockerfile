FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD target/akkaPinger-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]