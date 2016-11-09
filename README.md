Sample Service
==============

Sample project to run google cloud kubernetes / GKE

To run locally:
- Setup a mysql database with schema/user/password guestbook/guestbook/guestbook using `create schema guestbook` and `GRANT ALL PRIVILEGES ON guestbook.* TO 'guestbook'@'%' IDENTIFIED BY 'guestbook';
`
- `mvn clean package`
- Initialize the database the first time with `java -jar target/sample-service-1.0-SNAPSHOT.jar db migrate app-config.yml`
- `docker build --tag=sample-service .`
- `docker run -e GUESTBOOK_JDBC_URL="jdbc:mysql://{your-local-ip}:3306/guestbook" sample-service`

## Updating Kubernetes to a new version of this application

Build and push a new docker image, with a new version tag, and then deploy it:

    docker build -t eu.gcr.io/${PROJECT_ID}/sample-service:1.0.2 .
    gcloud docker push eu.gcr.io/${PROJECT_ID}/sample-service:1.0.2
    kubectl rolling-update constantijns-rc --image eu.gcr.io/${PROJECT_ID}/sample-service:1.0.2
