FROM maven:3.8.4-jdk-8-slim as build
COPY customer-service/src /usr/src/app/src
COPY customer-service/pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:8-jdk-alpine
COPY --from=build /usr/src/app/target/customer-service.jar /usr/app/customer-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/app/customer-service.jar"]