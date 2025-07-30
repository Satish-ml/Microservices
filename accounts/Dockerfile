FROM openjdk:23-jdk-slim

LABEL "org.opencontainers.image.authors"="Satish"

COPY build/libs/accounts-service-1.0.0.jar accounts-service-1.0.0.jar

ENTRYPOINT ["java", "-jar", "accounts-service-1.0.0.jar"]