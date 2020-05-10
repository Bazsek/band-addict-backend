FROM adoptopenjdk/openjdk11:ubi
VOLUME /tmp
ADD build/libs/demo-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=local", "/app.jar"]
