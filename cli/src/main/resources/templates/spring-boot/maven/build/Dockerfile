FROM maven:3.6.3-openjdk-11 as builder

WORKDIR /code
COPY . /code

RUN mvn package

FROM openjdk:11-slim

WORKDIR /opt
COPY --from=builder /code/target/myoperator.jar /opt

CMD ["java", "-jar", "/opt/myoperator.jar"]
