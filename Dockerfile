FROM hub.int.klarna.net/java:1.8.0

EXPOSE 8085 8086
COPY . /boxed-api
WORKDIR /boxed-api

RUN ./gradlew -PbuildId=SNAPSHOT :clean :buildRpm

ENTRYPOINT ["/boxed-api/docker/start-boxed-api.sh"]
