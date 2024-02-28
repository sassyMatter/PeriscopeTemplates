# Use the official OpenJDK base image
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app


# parameterize the name,
# Copy the JAR file into the container
COPY target/demo-0.0.1-SNAPSHOT.jar /app/demo.jar

# parameterize the port
# Expose the port that your Spring Boot app runs on
EXPOSE 8080:8080

# parmeterize the demo.jar file
# Command to run the application
CMD ["java", "-jar", "demo.jar"]
