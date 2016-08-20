#!/bin/bash
set -e

DATADIR='/var/lib/mysql'

if [ "${1:0:1}" = '-' ]; then
	set -- mysqld "$@"
fi

if [ ! -d "$DATADIR/mysql" -a "${1%_safe}" = 'mysqld' ]; then
	mysql_install_db

	cat /tmp/root.sql /tmp/schema.sql /tmp/structure.sql /tmp/data.sql > /tmp/init.sql

	set -- "$@" --init-file="/tmp/init.sql"
fi

chown -R mysql:mysql "$DATADIR"
exec "$@"