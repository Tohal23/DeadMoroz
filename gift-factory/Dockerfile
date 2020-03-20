FROM openjdk:8-jdk-alpine

VOLUME /tmp

EXPOSE 8082

ADD gift-factory/target/gift-factory-1.0.0.jar gift-factory-1.0.0.jar

ENTRYPOINT ["java","-jar", "gift-factory-1.0.0.jar"]