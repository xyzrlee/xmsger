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
 && apk add --no-cache openjdk11-jdk sudo \
 # Build & install
 && cd /repo \
 && chmod +x mvnw \
 && ./mvnw clean package ${MVNWARGS}\
 && mkdir -p /xmsger-boot /xmsger \
 && cp target/xmsger.jar /xmsger-boot/ \
 && rm -rf /repo \
 && rm -rf ${HOME}/.m2 \
 && ls -l /xmsger-boot

ENV PATH=${PATH}:/usr/lib/jvm/java-11-openjdk/bin

COPY entrypoint.sh /xmsger-boot/entrypoint.sh

ENV JVMARGS=

ENTRYPOINT /xmsger-boot/entrypoint.sh
