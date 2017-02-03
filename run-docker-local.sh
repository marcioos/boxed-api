#!/bin/sh

set_docker_host_ip() {
  export DOCKERHOST=$(ifconfig | grep -E "([0-9]{1,3}\.){3}[0-9]{1,3}" | grep -v 127.0.0.1 | awk '{ print $2 }' | cut -f2 -d: | head -n1)
}

set_docker_host_ip
docker-compose down -v --remove-orphans
docker-compose pull
docker-compose up -d
