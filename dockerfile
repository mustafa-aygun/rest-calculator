# ---- stage 1: build ----
FROM maven:3.9.4-eclipse-temurin-21 AS build

WORKDIR /workspace
COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests -B

# ---- stage 2: runtime ----
FROM eclipse-temurin:21 AS run

# Create non-root user for security
RUN useradd --create-home appuser
USER appuser

WORKDIR /home/appuser

# Copy the JAR from the build stage
COPY --from=build /workspace/target/*.jar app.jar

EXPOSE 8081

# Use exec form for ENTRYPOINT
ENTRYPOINT ["java", "-jar", "app.jar"]
