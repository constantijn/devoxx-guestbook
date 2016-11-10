Sample Service
==============

Sample project to run google cloud kubernetes / GKE

To run locally:
- Setup a mysql database with schema/user/password guestbook/guestbook/guestbook using `create schema guestbook` and `GRANT ALL PRIVILEGES ON guestbook.* TO 'guestbook'@'%' IDENTIFIED BY 'guestbook';`
- `mvn clean package`
- Initialize the database the first time with `java -jar target/devoxx-guestbook-1.0-SNAPSHOT.jar db migrate app-config.yml`
- `docker build --tag=sample-service .`
- `docker run -e GUESTBOOK_JDBC_URL="jdbc:mysql://{your-local-ip}:3306/guestbook" sample-service`
