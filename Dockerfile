FROM eclipse-temurin:22

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src

ENTRYPOINT ["./mvnw", "spring-boot:run"]