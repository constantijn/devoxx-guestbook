FROM jolokia/alpine-jre-8
MAINTAINER cvisinescu@xebia.com
WORKDIR /
COPY target/devoxx-guestbook-1.0-SNAPSHOT.jar /app/app.jar
COPY app-config.yml /app/app-config.yml
ENTRYPOINT [ "java", "-jar", "/app/app.jar", "server", "/app/app-config.yml" ]
EXPOSE 8080
