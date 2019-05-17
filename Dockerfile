#
# Dockerfile for api
#

FROM alpine
LABEL maintainer="Ricky Li <cnrickylee@gmail.com>"

USER root

ENV MVNWARGS="-Dmaven.test.skip=true --batch-mode --show-version --no-transfer-progress"

copy xmsger /repo

RUN set -ex \
 # Build environment setup
 && apk update \
 && apk add --no-cache --virtual .build-deps \
      openjdk8 \
      maven \
      git \
 # Build & install
 && cd /repo \
 && chmod +x mvnw \
 && ./mvnw clean package ${MVNWARGS}\
 && mkdir -p /xmsger \
 && cp target/xmsger.jar /xmsger/ \
 && ./mvnw clean \
 && rm -rf /repo \
 && rm -rf ${HOME}/.m2 \
 && apk del .build-deps \
 && ls -l /xmsger \
 && apk add --no-cache openjdk8-jre

COPY entrypoint.sh /xmsger/entrypoint.sh

ARG JVMARGS=

ENTRYPOINT /xmsger/entrypoint.sh ${JVMARGS}
