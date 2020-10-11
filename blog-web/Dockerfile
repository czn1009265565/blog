FROM openjdk:8-jdk-alpine

RUN mkdir /code
WORKDIR /code
COPY ./target/blog-web-0.0.1.jar /code/blog-web-0.0.1.jar

CMD java -jar -Xms256m -Xmx256m blog-web-0.0.1.jar