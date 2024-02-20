FROM openjdk:17
WORKDIR /app
COPY ./target/naves-espaciales-proyect-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java", "-jar", "naves-espaciales-proyect-0.0.1-SNAPSHOT.jar"]