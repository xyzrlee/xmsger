#
# Dockerfile for api
#

FROM alpine
LABEL maintainer="Ricky Li <cnrickylee@gmail.com>"

USER root

ARG MVNWARGS
ARG JVMARGS

RUN set -ex \
 # Build environment setup
 && apk update \
 && apk add --no-cache --virtual .build-deps \
      openjdk8 \
      maven \
      git \
 # Build & install
# && git clone https://github.com/xyzrlee/xmsger.git /tmp/repo/xmsger \
 && mkdir -p /tmp/repo/xmsger
 && cp -r xmsger /tmp/repo/xmsger/xmsger
 && cd /tmp/repo/xmsger/xmsger \
 && chmod +x mvnw \
 && ./mvnw clean package ${MVNWARGS}\
 && mkdir -p /xmsger \
 && cp target/xmsger.jar /xmsger/ \
 && ./mvnw clean \
 && rm -rf /tmp/repo/xmsger \
 && rm -rf ${HOME}/.m2 \
 && apk del .build-deps \
 && ls -l /xmsger \
 && apk add --no-cache openjdk8-jre

COPY entrypoint.sh /xmsger/entrypoint.sh

ENTRYPOINT /xmsger/entrypoint.sh ${JVMARGS}
