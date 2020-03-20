FROM openjdk:8-jdk-alpine

VOLUME /tmp

EXPOSE 8080

ADD dead-moroz/dead-moroz-impl/target/dead-moroz-impl-1.0.0.jar dead-moroz-impl-1.0.0.jar

ENTRYPOINT ["java","-jar","dead-moroz-impl-1.0.0.jar"]