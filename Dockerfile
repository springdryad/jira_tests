FROM circleci/openjdk:8u222-stretch-browsers
MAINTAINER Ruslana
USER root
RUN apt-get update && apt-get install maven
WORKDIR /usr/app
COPY . /usr/app
CMD mvn test