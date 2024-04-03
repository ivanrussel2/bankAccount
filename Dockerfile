FROM openjdk:17-jdk-slim
LABEL authors="ivanr"
COPY target/*.jar /app/exaltbank.jar
CMD ["java","-jar","/app/exaltbank.jar"]