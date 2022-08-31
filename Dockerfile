FROM maven:3.8.1-jdk-11 AS build

RUN mkdir /proboarding

WORKDIR /proboarding

COPY . .

RUN mvn install

WORKDIR target/

CMD ["java","-jar","/proboarding/target/onboarding-web-server-0.0.1-SNAPSHOT.jar"]

