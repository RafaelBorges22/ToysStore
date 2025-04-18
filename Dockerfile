FROM openjdk:21-jdk-slim

WORKDIR /app

COPY . .

RUN mvm clean package -DskipTests && mvm maven package

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} /app/app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]