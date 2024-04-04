FROM openjdk:17-jdk-slim
LABEL authors="ivanr"
EXPOSE 8080
COPY target/*.jar /app/exaltbank.jar
ENTRYPOINT ["java","-jar","/app/exaltbank.jar"]