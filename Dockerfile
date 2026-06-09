FROM maven:3.9-eclipse-temurin-11 AS build

WORKDIR /workspace

COPY pom.xml maven-version-rules.xml ./
COPY src ./src

RUN mvn --batch-mode --no-transfer-progress package -DskipTests


FROM eclipse-temurin:11-jre-jammy

RUN useradd --create-home --uid 10001 arewegreen \
    && mkdir -p /home/arewegreen/arewegreen \
    && chown arewegreen:arewegreen /home/arewegreen/arewegreen

WORKDIR /app

COPY --from=build /workspace/target/arewegreen-*.jar /app/arewegreen.jar

ENV START_BROWSER_AUTOMATICALLY=false

VOLUME ["/home/arewegreen/arewegreen"]
EXPOSE 8080

USER arewegreen

ENTRYPOINT ["java", "-jar", "/app/arewegreen.jar"]
