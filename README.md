# InnaEisenProductsService

This project is SpringBoot3 based API Server, provided REST API for CRUD Products Example
## Dockerizing

Clone project from github to any host, with Docker installed

Perform the following commands:
```bash
>cd inna-eisen-products-service
~/inna-eisen-products-service$ sudo apt install openjdk-17-jdk
~/inna-eisen-products-service$ chmod +x ./gradlew
~/inna-eisen-products-service$ sudo ./gradlew build
~/inna-eisen-products-service$ sudo docker build -t inna-eisen-products-service .
~/inna-eisen-products-service$ sudo docker images
REPOSITORY                    TAG           IMAGE ID       CREATED              SIZE
inna-eisen-products-service   latest        dfff0186e40d   About a minute ago   461MB

~/inna-eisen-products-service$ sudo docker run -d -p 8081:8081 --name inna-service inna-eisen-products-service   
```
## Docker-compose using

The "docker-compose" tool enable you more convenient way to run all needed components by the single cli command
and to have all definition into single yaml file. There is docker-compose file into root project directory -
docker-compose-inna-poducts.yaml. You need install docker-compose feature in testing host in case of usage.
There are 3 components, running by docker-compose as "services": server, ui-service and db (mysql)).
Use docker-compose and definitions yaml file as following:
```bash
eisen@ubuntu-2:~$ cp inna-eisen-products-service/docker-compose-inna-poducts.yaml .
eisen@ubuntu-2:~$ sudo docker-compose -f docker-compose-inna-poducts.yaml up --detach
check status:
eisen@ubuntu-2:~$ sudo docker-compose -f docker-compose-inna-poducts.yaml ps
       Name                     Command               State                          Ports
-----------------------------------------------------------------------------------------------------------------
eisen_db_1           docker-entrypoint.sh mysqld      Up      0.0.0.0:3306->3306/tcp,:::3306->3306/tcp, 33060/tcp
eisen_server_1       java -jar /app/app.jar           Up      0.0.0.0:8081->8081/tcp,:::8081->8081/tcp
eisen_ui-service_1   docker-entrypoint.sh ng se ...   Up      0.0.0.0:8082->4200/tcp,:::8082->4200/tcp



```
The nice feature: docker-compose creates default internal network for all 3 services (containers, actually),
so, each service may connect to every other by its "service name". Say, "server" may connect to db component as
https://db:3306. 
 Once we have all components up and running, you can open browser against you testing host with URL:
http://<my-testing-host-ip>:8082

IMPORTANT NOTE:

The API endpoint, used by UI application is "ubuntu-2", which should be resolved to <my-testing-host-ip>,
either by /etc/hosts or by some DNS server, if such exists.