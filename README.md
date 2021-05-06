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
sys / Oradoc_db1 (SYSDBA)
localhost:1521
ServiceName: ORCLCDB.localdomain
ServiceName: ORCLPDB1.localdomain
```
```
sergo / O123seR_go123 (SYSKM)
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

