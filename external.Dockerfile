FROM gradle:5.3.0-jdk11-slim
USER root
WORKDIR /usr/src/java-code
COPY . /usr/src/java-code/

RUN gradle :external:bootJar --stacktrace

WORKDIR /usr/src/java-app

RUN cp /usr/src/java-code/external/build/libs/*.jar ./app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
