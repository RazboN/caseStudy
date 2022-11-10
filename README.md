# to run the application

### 1
docker run -p 5432:5432 --name YKBHR -e POSTGRES_PASSWORD=ykb123  postgres

### 2
create database named YKB_HR_DB vie pgAdmin

### 3
run command

java -jar target/annual-leave-module-0.0.1-SNAPSHOT.jar annual-leave-module
