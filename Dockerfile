FROM adoptopenjdk/openjdk8:latest

USER root

COPY target/traveler-profile-0.0.1-SNAPSHOT.jar /deployments/

ENTRYPOINT ["sh", "-c", "java -jar /deployments/traveler-profile-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080