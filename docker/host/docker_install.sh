#!/bin/bash
DEBIAN_FRONTEND=noninteractive apt-get update
DEBIAN_FRONTEND=noninteractive apt-get -y install curl
DEBIAN_FRONTEND=noninteractive apt-get upgrade
curl -sSL https://get.docker.com/ | sh
gpasswd -a vagrant docker
service docker restart
ps aux | grep 'sshd:' | awk '{print $2}' | xargs kill