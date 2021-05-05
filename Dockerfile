FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/product-1.0.0.RELEASE.jar
COPY ${JAR_FILE} product-1.0.0.RELEASE.jar
ENTRYPOINT ["java","-jar","/product-1.0.0.RELEASE.jar"]