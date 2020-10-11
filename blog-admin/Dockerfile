FROM openjdk:8-jdk-alpine

RUN mkdir /code
WORKDIR /code
COPY ./target/blog-admin-0.0.1.jar /code/blog-admin-0.0.1.jar

CMD java -jar -Xms256m -Xmx256m blog-admin-0.0.1.jar