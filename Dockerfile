FROM tomcat:8-jre8
COPY target/*.war /usr/local/tomcat/webapps/faw.war
COPY tomcat-users.xml /usr/local/tomcat/conf/tomcat-users.xml
ENV DATABASE_URL=mysql://faw:faw@localhost:3306/faw