FROM openjdk:21
ARG JAR_FILE=api/build/libs/*-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]\
EXPOSE 8080
