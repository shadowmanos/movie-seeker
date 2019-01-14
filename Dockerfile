FROM openjdk:11-jre-slim
ADD target/*.jar application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]