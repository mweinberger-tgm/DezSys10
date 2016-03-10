#!/bin/sh

ALGORITHM=roundrobin
CONF_PATH=/etc/haproxy/haproxy.cfg

apt-get update

apt-get install haproxy -y

echo "
frontend localnodes
	bind *:80
	mode http
	default_backend nodes
	
backend nodes
	mode http
	balance $ALGORITHM
	option forwardfor

	server web01 192.168.0.11:80 check
	server web02 192.168.0.12:80 check
	server web03 192.168.0.13:80 check" >> $CONF_PATH

haproxy -f $CONF_PATH

service haproxy reload