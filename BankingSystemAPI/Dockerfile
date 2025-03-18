# Use a valid base image (replace 23-jdk with your Java version)
FROM eclipse-temurin:23-jdk

# Metadata
LABEL maintainer="santoshrh.1110@gmail.com"

# Set working directory
WORKDIR /app

# Copy the built JAR file into the container
COPY target/BankingSystemAPI-0.0.1-SNAPSHOT.jar /app/BankingSystemAPI.jar

EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "BankingSystemAPI.jar"]
