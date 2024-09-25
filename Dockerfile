FROM openjdk:21-jdk

WORKDIR /app

COPY target/incomeManagementService-0.0.1-SNAPSHOT.jar /app/readyApp.jar

EXPOSE 8080

CMD ["java", "-jar", "readyApp.jar"]

