FROM openjdk:8-jre
ADD ./handleData-api/target/*.jar application.jar

ENTRYPOINT ["java -jar application.jar"]
