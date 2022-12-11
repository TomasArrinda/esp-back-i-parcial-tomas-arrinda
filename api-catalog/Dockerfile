FROM openjdk:17-alpine3.12
ARG JAR_FILE=target/dh-api-catalog.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 8081