Docker
---
Compile ```.jar``` file from sources:```mvn package -f ./pom.xml```
<br>Build image:```sudo docker build -f docker/Dockerfile -t spring-boot-docker .```
<br>Run application:```sudo docker run -p 8080:8080 spring-boot-docker```
<hr/>
Run Oracle DB: 
sudo docker run -d --name oracle-db -p 1521:1521 store/oracle/database-enterprise:12.2.0.1
<hr/>
<br>Stop containers: ```sudo docker stop $(sudo docker ps -aq)```
<br>Delete container```sudo docker container rm 123```
External properties
---
You can overwrite properties in ```docker/application.properties```
<br>To check properties:
```curl -XGET -H "Content-type: application/json" 'http://localhost:8080/actuator/env'```

Connection to Oracle DB
---
```
SqlDeveloper: sys / Oradoc_db1 (SYSDBA)
IDEA: sys as sysdba / Oradoc_db1 
localhost:1521
ServiceName: ORCLCDB.localdomain
ServiceName: ORCLPDB1.localdomain
```
At first start, run ```sql-oracle/create user.sql``` under ```sys``` user at ```ORCLPDB1.localdomain```
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
To run migration run ```sql-oracle/create schema.sql``` by user ```testuser``` 

Migrations DB
------
Migrations work by Liquibase.<br>
To specify credentials and connect ti DB, edit file: liquibase.properties
<br>
To Run migrations run command:
```
mvn -f ./migration-db/pom.xml liquibase:update
```
