FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar E-Commerce_BackEnd.jar
ENTRYPOINT ["java","-jar","/E-Commerce_BackEnd.jar"]
EXPOSE 8081