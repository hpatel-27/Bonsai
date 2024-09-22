# Use a base image with Java pre-installed
FROM openjdk:11-jre-slim
# Copy the built Spring Boot JAR file into the container
COPY target/Bonsai-Fitness-0.0.1-SNAPSHOT.jar /app.jar
# Expose the port that Spring Boot is running on
EXPOSE 8080
# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app.jar"]
