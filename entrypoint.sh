#!/bin/sh

cd /xmsger
ls -lh
sudo -u ${RUNAS} java ${JVMARGS} -jar xmsger.jar
