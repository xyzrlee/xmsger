#!/bin/sh

cd /xmsger-boot
ls -lh
sudo -u ${RUNAS} java ${JVMARGS} -jar xmsger.jar
