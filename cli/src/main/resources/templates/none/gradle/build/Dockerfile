FROM gradle:6.7.1-jdk11 as builder

WORKDIR /code
COPY . /code

RUN gradle shadowJar --no-daemon

FROM openjdk:11-slim

WORKDIR /opt
COPY --from=builder /code/build/libs/myoperator.jar /opt

CMD ["java", "-jar", "/opt/myoperator.jar"]
