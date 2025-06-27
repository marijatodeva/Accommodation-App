# One-stage: Build + Run inside Maven image
FROM maven:3.9.6-eclipse-temurin-21

# Set working directory
WORKDIR /app

# Copy full project
COPY . .

# Build the project
RUN mvn clean package -DskipTests && cp target/*.jar app.jar

# Expose the port
EXPOSE 9090

# Run the application (assumes jar is created in target/)
CMD ["java", "-jar", "app.jar"]
