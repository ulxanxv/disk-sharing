FROM openjdk:8
COPY target/*.jar disk-sharing.jar
ENTRYPOINT ["java", "-jar", "disk-sharing.jar"]