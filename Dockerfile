#
# Dockerfile for api
#

FROM alpine as builder

ENV MVNWARGS="-Dmaven.test.skip=true -Dmaven.javadoc.skip=true --batch-mode --show-version --no-transfer-progress"

COPY xmsger /repo

RUN set -ex \
 # Build environment setup
 && apk update \
 && apk add --no-cache openjdk11-jdk \
 # Build & install
 && cd /repo \
 && chmod +x mvnw \
 && ./mvnw clean package ${MVNWARGS}\
 && mkdir -p /xmsger-boot /xmsger \
 && cp target/xmsger.jar /xmsger-boot/ \
 && ls -l /xmsger-boot

FROM alpine

COPY --from=builder /xmsger-boot /xmsger-boot

RUN set -ex \
 # Build environment setup
 && apk update \
 && apk add --no-cache openjdk11-jdk \
 && java -version \
 && ls -l /xmsger-boot

#ENV PATH=${PATH}:/usr/lib/jvm/java-11-openjdk/bin

COPY entrypoint.sh /xmsger-boot/entrypoint.sh

ENV JVMARGS=

ENTRYPOINT /xmsger-boot/entrypoint.sh

LABEL maintainer="Ricky Li <cnrickylee@gmail.com>"
