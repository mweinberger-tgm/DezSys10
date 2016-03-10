#!/bin/sh

CONF_PATH=/var/www/html/index

apt-get update

apt-get install apache2 -y
apt-get install php5 -y

rm $CONF_PATH.html
echo "<?php 
	echo \$_SERVER['SERVER_ADDR'];
?>" > $CONF_PATH.php