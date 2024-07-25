FROM maven:3.9.8-amazoncorretto-21 AS build
WORKDIR /build
COPY src ./src
COPY pom.xml ./

RUN mvn clean package -e -DskipTests

FROM amazoncorretto:21
WORKDIR /app
COPY --from=build /build/target/HT-49*jar ./ht-49.jar
EXPOSE 8089
CMD ["java","-jar","ht-49.jar"]