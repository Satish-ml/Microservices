#Start with a base image containing Java runtime
FROM openjdk:21-jdk-slim

# MAINTAINER instruction is deprecated in favor of using label
# MAINTAINER eazybytes.com
#Information around who maintains the image
LABEL "Satish.com

# Add the application's jar to the image
COPY build/libs/loans-Service-1.0.0.jar loans-Service-1.0.0.jar

# execute the application
ENTRYPOINT ["java", "-jar", "loans-Service-1.0.0.jar"]