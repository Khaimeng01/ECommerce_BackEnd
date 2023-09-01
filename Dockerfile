FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/E-Commerce_BackEnd-0.0.1-SNAPSHOT.jar E-Commerce_BackEnd.jar
ENTRYPOINT ["java","-jar","/E-Commerce_BackEnd.jar"]
EXPOSE 8081
