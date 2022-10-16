FROM adoptopenjdk/openjdk11-openj9 as builder
RUN apt-get update

WORKDIR /url-shortener/service

ARG UNAME=developer
ARG userID=${userID:-1000}
ARG groupID=${groupID:-1000}
RUN groupadd -g $groupID -o $UNAME
RUN useradd -l -d /app/source/urlshortener -u $userID -g $groupID -o -s /bin/bash $UNAME
USER $UNAME

ENV JPDA_ADDRESS=5006 JPDA_TRANSPORT=dt_socket

EXPOSE  8080 5006