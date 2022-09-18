#
# Build stage
#
FROM maven:3.8.5-jdk-11-slim AS build
COPY src src
COPY pom.xml pom.xml
RUN mvn -f pom.xml clean package

#
# Package stage
#
FROM openjdk:11-jre-slim
COPY target/app.jar app.jar
ENTRYPOINT ["java","-jar","./app.jar"]
