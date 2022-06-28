java -jar /app/handleData-api.jar --server.port=8080 &
java -jar /app/handleData-core.jar --server.port=8081 &
java -jar /app/handleData-emailAlarm.jar --server.port=8082 &
