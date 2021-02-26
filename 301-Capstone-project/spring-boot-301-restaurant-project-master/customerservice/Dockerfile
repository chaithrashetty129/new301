FROM openjdk:8

COPY ./target/customerservice-*.jar customerservice.jar

EXPOSE 8083

CMD ["java","-jar","-Dspring.profile.active=dev","customerservice.jar"]