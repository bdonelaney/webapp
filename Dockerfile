FROM openjdk:8-buster
COPY target/spring-boot-web-application-*.jar /app.jar
CMD ["java","-Dserver.port=3333","-jar","/app.jar"]
