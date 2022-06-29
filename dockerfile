FROM kevinyan001/aliyun-mvn:0.0.1 AS MAVEN_BUILD

COPY ./ /build/
COPY ./entrypoint.sh /application/entrypoint.sh
RUN chmod +x /application/entrypoint.sh

WORKDIR /build/
# mount anonymous host directory as .m2 storage for contianer
VOLUME /root/.m2
RUN mvn clean package -Dmaven.test.skip=true --quiet

FROM openjdk:8-jre
COPY --from=MAVEN_BUILD /build/handleData-api/target/*.jar /app/handleData-api.jar
COPY --from=MAVEN_BUILD /build/handleData-core/target/*.jar /app/handleData-core.jar
COPY --from=MAVEN_BUILD /build/handleData-emailAlarm/target/*.jar /app/handleData-emailAlarm.jar

ENTRYPOINT ["java -jar /app/handleData-api.jar --server.port=8080 & java -jar /app/handleData-core.jar --server.port=8081 & java -jar /app/handleData-emailAlarm.jar --server.port=8082 &"]
