FROM openjdk:17

ARG WAR_FILE=target/*.war

COPY ./target/youmarket-0.0.1-SNAPSHOT.war app.war

ENTRYPOINT ["java", "-jar","app.war"]

EXPOSE 9000