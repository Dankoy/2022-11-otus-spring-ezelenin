FROM  --platform=linux/amd64 maven:3.9-eclipse-temurin-11
LABEL authors="turtality"

# мастер пароль jasypt
ARG JASYPT_PASSWORD
ENV ENV_JASYPT_PASSWORD $JASYPT_PASSWORD

# игнорируется билд фронта с помощью профилей в мавене
ARG MAVEN_PROFILE
ENV ENV_MAVEN_PROFILE $MAVEN_PROFILE

WORKDIR /app
COPY ./src ./src
COPY ./pom.xml ./
RUN ["/bin/bash", "-c", "mvn clean install -P $ENV_MAVEN_PROFILE"]

# entrypoint не работает с шеллом, работает только ARG переменные
ENTRYPOINT ["java", "-Djasypt.encryptor.password=${JASYPT_PASSWORD}", "-Djasypt.encryptor.algorithm=PBEWithMD5AndDES", "-Djasypt.encryptor.ivGeneratorClassName=org.jasypt.iv.NoIvGenerator", "-jar", "target/hw-17-0.0.1-SNAPSHOT.jar"]