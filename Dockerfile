#
# Dockerfile for api
#

FROM alpine
LABEL maintainer="Ricky Li <cnrickylee@gmail.com>"

USER root

ENV MVNWARGS="-Dmaven.test.skip=true -Dmaven.javadoc.skip=true --batch-mode --show-version --no-transfer-progress"

COPY xmsger /repo

RUN set -ex \
 # Build environment setup
 && apk update \
 && apk add --no-cache openjdk8 openjdk8-jre sudo \
 # Build & install
 && cd /repo \
 && chmod +x mvnw \
 && ./mvnw clean package ${MVNWARGS}\
 && mkdir -p /xmsger \
 && cp target/xmsger.jar /xmsger/ \
 && rm -rf /repo \
 && rm -rf ${HOME}/.m2 \
 && ls -l /xmsger

ENV PATH=${PATH}:/usr/lib/jvm/java-1.8-openjdk/bin

COPY entrypoint.sh /xmsger/entrypoint.sh

ENV RUNAS=root
ENV JVMARGS=

ENTRYPOINT /xmsger/entrypoint.sh