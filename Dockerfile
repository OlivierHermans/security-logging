FROM openjdk:11.0-jre

COPY deployment/security-logging-packaging.jar /opt/security-logging-packaging.jar

CMD ["java", "-jar", "/opt/security-logging-packaging.jar"]