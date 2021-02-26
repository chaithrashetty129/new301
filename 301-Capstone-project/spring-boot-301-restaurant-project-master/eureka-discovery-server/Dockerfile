FROM openjdk:8

COPY ./target/EurekaDiscoveryService-*.jar EurekaDiscoveryService.jar

EXPOSE 8020

CMD ["java","-jar","-Dspring.profile.active=dev","EurekaDiscoveryService.jar"]