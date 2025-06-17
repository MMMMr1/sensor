FROM openjdk:21
MAINTAINER MARINA
ADD target/monitor-sensors-0.0.1-SNAPSHOT.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/monitor-sensors-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080