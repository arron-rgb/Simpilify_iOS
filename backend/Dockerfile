FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

ADD target/info6350-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 7759
ENTRYPOINT ["java", "-jar", "/app.jar"]
