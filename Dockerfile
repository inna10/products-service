# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app
ARG JAR_FILE=build/libs/inna-eisen-products-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
# Set the command to run the application
ENTRYPOINT ["java","-jar","/app/app.jar"]