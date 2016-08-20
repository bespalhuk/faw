# Faces - Web Template Project.
- JavaServer Faces
- Spring Boot, Security and Webflow
- Winter-Data-JPA and Querydsl
- TestNG
- Vagrant using Docker as provider (with MySQL image)

# LINUX
<b>Steps for development:</b><br />
1º - Install Docker and Vagrant.<br />
2º - On project's root directory: <code>vagrant up server --provider=docker</code><br />
3º - Execute <code>run-maven.sh</code><br />
4º - Access: <code>http://localhost:8080/faw</code><br />

<b>Steps for production:</b><br />
1º - Install Docker.<br />
2º - Get Docker's IP: <code>sudo ip addr show docker0</code><br />
3º - Install MySQL and edit config file binding Docker's IP.<br />
4º - Create schema and grant privileges. e.g.:<br />
<code>CREATE DATABASE faw;
GRANT ALL PRIVILEGES ON faw.* to 'faw'@'%' identified by 'faw';</code><br />
5º - In <code>run-docker.sh</code> file, <code>DATABASE_URL</code>'s IP must be the same as Docker's.<br />
6º - Execute <code>run-docker.sh</code> with schema's name, port and (Docker) image tag. e.g.:<br />
<code>./run-docker faw 8080 1</code><br />
7º - Access localhost based on the port you chose. e.g.: <code>http://localhost:8080/faw</code><br />
8º - Execute <code>run-docker.sh</code> for each schema you create. Port must be unique.<br />