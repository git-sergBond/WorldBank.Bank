Docker
====================================
Compile ```.jar``` file from sources:```mvn package -f ./pom.xml```</br>
Build image:```sudo docker build -f docker/Dockerfile -t spring-boot-docker .```</br>
Run application:```sudo docker run -p 8080:8080 spring-boot-docker```</br>

External properties
====================================
You can overwrite properties in ```docker/application.properties```

API for test:
============================================
Example: get properties:
```sh
curl -XGET -H "Content-type: application/json" 'http://localhost:8080/actuator/env'
```