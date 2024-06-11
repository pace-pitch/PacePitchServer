FROM openjdk:17-alpine AS base

RUN apk add ffmpeg

FROM base AS build

WORKDIR /build

COPY . .

RUN --mount=type=cache,target=/root/.gradle ./gradlew --no-daemon -i clean build -x test

FROM build AS run

WORKDIR /app

COPY --from=build /build/build/libs/demo-0.0.1-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java","-jar","/app/app.jar"]