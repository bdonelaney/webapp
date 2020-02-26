FROM openjdk:8-jre-alpine
COPY target/spring-boot-web-application-*.jar /app.jar
CMD ["java","-Dserver.port=3333","-jar","/helloWorld.jar"]
