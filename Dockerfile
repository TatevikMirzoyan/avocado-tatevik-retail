FROM openjdk:11-alpine
MAINTAINER Tatev
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} avocado-tatevik-retail.jar

ENTRYPOINT ["java","-jar","/avocado-tatevik-retail.jar"]