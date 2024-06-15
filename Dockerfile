# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim
# Set the working directory in the container
WORKDIR /app
# Copy the project files
COPY . .
# Build the application
RUN ./mvnw clean package -Pproduction
# Expose the port the app runs on
EXPOSE 8080
# Run the jar file
ENTRYPOINT ["java", "-jar", "target/ImageTwister-0.0.1-SNAPSHOT.jar"]