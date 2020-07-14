FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 9005
ADD target/*.jar servicio-usuarios.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /servicio-usuarios.jar" ]