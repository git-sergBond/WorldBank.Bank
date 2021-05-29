API [webapp-swagger-ui](http://localhost:8080/swagger-ui/)
---

Stack
---
```
Lang: Java (GraalVM)
Framework: Spring (Boot, Data, Liquibase)
API: openapi-generator, openapi-ui
DB: Oracle 
```

Docker
---
1. Compile ```.jar``` file from sources:```mvn package -f ./pom.xml```
<br>Build image:```sudo docker build -f docker/Dockerfile -t spring-boot-docker .```
<br>Run application:```sudo docker run -p 8080:8080 spring-boot-docker```

2. Run Oracle DB: 
```sudo docker run -d --name oracle-db -p 1521:1521 store/oracle/database-enterprise:12.2.0.1```
3. <i>External properties</i>.
Properties can be owerwriten in file.
```docker/application.properties```
To see properties:
```curl -XGET -H "Content-type: application/json" 'http://localhost:8080/actuator/env'```

Migrations DB (Liquibase)
------
1. <i>Create users for this DB.</i>
Specify credentials and connect ti DB, edit file: liquibase.properties.
And run command:
```mvn -f ./create-user-db/pom.xml liquibase:update```
2. <i>Create schema AND run migrations.<i> Specify credentials and connect ti DB, edit file: liquibase.properties.
And run command:
```mvn -f ./migration-db/pom.xml liquibase:update```
   
Connection to Oracle DB
---
```
SqlDeveloper: sys / Oradoc_db1 (SYSDBA)
IDEA: sys as sysdba / Oradoc_db1 
localhost:1521
ServiceName: ORCLCDB.localdomain
ServiceName: ORCLPDB1.localdomain
```
```
testuser / testuser (SYSKM)
localhost:1521
ServiceName: ORCLPDB1.localdomain
```
if you can not connect to Oracle running under Docker, this can solve you problem:
```
sudo nano /etc/docker/daemon.json 
{
    "userland-proxy": false
}
sudo systemctl restart docker
```