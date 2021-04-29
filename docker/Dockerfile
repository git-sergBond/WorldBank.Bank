FROM openjdk:11-jdk-slim
COPY ./webapp/target/webapp-0.0.1-SNAPSHOT.jar /
ADD docker/application.properties /application.properties
ENTRYPOINT ["java","-jar","/webapp-0.0.1-SNAPSHOT.jar"]