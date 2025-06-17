FROM openjdk:21
MAINTAINER MARINA
ADD target/statistics-0.0.1-SNAPSHOT.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/statistics-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080