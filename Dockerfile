FROM openjdk:12-alpine

COPY target/BookingApp-*.jar /BookingApp.jar
COPY ./mydb.db ./mydb.db

CMD ["java", "-jar", "/BookingApp.jar"]