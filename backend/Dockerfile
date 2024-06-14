FROM maven:3.9.6 as builder

WORKDIR /opt/onesecret

COPY . .

RUN mvn clean

RUN mvn dependency:resolve

RUN mvn compile

RUN mvn package

FROM openjdk:21-jdk

WORKDIR /opt/onesecret

COPY --from=builder /opt/onesecret .

CMD ["java", "-jar", "target/onesecret-0.0.1-SNAPSHOT.jar", "--spring.flyway.locations=classpath:src/main/resources/db/migration"]
