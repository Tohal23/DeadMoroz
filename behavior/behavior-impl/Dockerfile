FROM openjdk:8-jdk-alpine

VOLUME /tmp

EXPOSE 8081

ADD behavior/behavior-impl/target/behavior-impl-1.0.0.jar behavior-impl-1.0.0.jar

ENTRYPOINT ["java","-jar","behavior-impl-1.0.0.jar"]