FROM openjdk:12-alpine

COPY target/dockerTry.jar /BookingApp.jar
COPY target/lib /lib
COPY ./dbLite.db ./dbLite.db

CMD ["java", "-jar", "/BookingApp.jar"]