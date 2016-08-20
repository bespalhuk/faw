FROM mysql:5.6.32
COPY entrypoint.sh /entrypoint.sh
COPY sql/root.sql /tmp/root.sql
COPY sql/schema.sql /tmp/schema.sql
COPY sql/structure.sql /tmp/structure.sql
COPY sql/data.sql /tmp/data.sql
ENTRYPOINT ["/entrypoint.sh"]
CMD ["mysqld"]