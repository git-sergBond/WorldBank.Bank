WorldBank
===

TODO
---
1. Change: spring-boot to spring-cloud in webapp-openapi

API 
---
1. <b>You can get contract by link</b> [openapi-ui](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config)
2. <b>Generate api</b> Execute command: 
    ```
    mvn -f ./world-bank-openapi/pom.xml clean compile
    ```

Roles
---
1. Client
2. Operator

Business process
---
1. Operator register Client with identity document (Passport).
2. Create card by Passport. Steps:
   1. Operator create issue request to card (identity document)
   2. Operator print form
   3. Client sign form
   4. Operator scan form
   5. Operator see status card (request, rejected, issued, transition, delivered, givenClient)
   5. Operator take card to owner (identity document)
3. Operator connect to Client by Passport. Items:
   1. Phone 
   2. Email 
4. Authenticate by:
   1. Card 
   2. Passport
   3. Phone 
   3. Email
5. Client connect to Self Client items:
   1. Phone
   2. Email
6. Client see balance.
7. Client input money to Card by ATM(Microservice) -> ABS(Microservice) -> WorldBank(Microservice).
8. Client transit money to another Client
9. Client transit money to another self Card

Stack
---
* Lang: Java GraalVM
* CodeGen: lombok, mapStruct
* Builder: Maven
* Framework: Spring Boot, REST, Spring Data
* Testing: Junit 5, Mockito
* API: openapi-generator, openapi-ui
* DB: Oracle, Liquibase
* Env: Docker, Ubuntu Server

Docker
---
1. <b>Run application</b>. Compile ```.jar``` file from sources:
    ```
    mvn clean package
    ```
    Build image:
   ```
   sudo docker build -f docker/Dockerfile -t spring-boot-docker .
   ```
<br>Run application:```sudo docker run -p 8080:8080 spring-boot-docker```

2. <b>Run Oracle DB</b>: 
    ```
    sudo docker login -u <USERNAME> -p <PASSWORD>
    sudo docker run -d --name oracle-db -p 1521:1521 store/oracle/database-enterprise:12.2.0.1
    ```
   OR
   ```
   sudo docker start oracle-db
   ```
3. <b>External properties</b>.
Properties can be owerwriten in file.
```docker/application.properties```
To see properties:
    ```
    curl -XGET -H "Content-type: application/json" 'http://localhost:8080/actuator/env'
    ```

Migrations DB (Liquibase)
------
1. <b>Create users for this DB.</b>
Specify credentials and connect ti DB, edit file: liquibase.properties.
And run command:
   ```
   mvn -f ./db-create-users/pom.xml liquibase:update
   ```
2. <b>Create schema AND run migrations.</b> Specify credentials and connect ti DB, edit file: liquibase.properties.
And run command:
   ```
   mvn -f ./db-migrations/pom.xml liquibase:update
   ```
   
Connection to Oracle DB
---
```
SqlDeveloper: sys / Oradoc_db1 (SYSDBA)
IDEA: sys as sysdba / Oradoc_db1
Navicat: system / Oradoc_db1
localhost:1521
ServiceName: ORCLCDB.localdomain
ServiceName: ORCLPDB1.localdomain
```
```
testuser / testuser (SYSKM)
localhost:1521
ServiceName: ORCLPDB1.localdomain
```

Kafka
---
Consumer integration with
```
Correlation schema: MessageID to ReplyID
MessageID save to (kafka-topic)atm-callback
To payload of message put replyTo="atm-reply"

ms-WorldBank listen (kafka-topic)atm-request
ms-WorldBank create topic with replyTo
ms-WorldBank send response to topic with replyTo
```

For run kafka broker:
```
sudo docker run --rm --net=host lensesio/fast-data-dev
```
Links:
- [UI for Kafka](http://127.0.0.1:3030/)
- [Documentation to the docker image](https://github.com/lensesio/fast-data-dev)

Troubleshooting (Ubuntu)
---
if you can not connect to Oracle running under Docker, this can solve you problem:
```
sudo nano /etc/docker/daemon.json 
{
    "userland-proxy": false
}
sudo systemctl restart docker
```