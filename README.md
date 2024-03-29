# AutoService
# Project description
REST API of auto service for getting cost of orders and masters salary and other CRUD operation
# Features
- Get Order cost
- Get master`s salary
- Add goods to Order
- Change Favor status
- Change Order status
- Get orders of (Master, Owner)
- Create objects (Master, Car, Owner, Order, Favor, Goods)
- Update objects (Master, Car, Owner, Order, Favor, Goods)

Also you can select max discount for client [here](src/main/java/autoservise/service/impl/OrderServiceImpl.java) and
percentage of master`s salary [here](src/main/java/autoservise/service/impl/MasterServiceImpl.java)

# Technologies
- Maven
- JDK 17
- Docker
- Lombok
- Data JPA
- Hibernate
- PostgreSQL
- Tomcat 9.0.50
- SpringBoot (Web)

# Project structure
Project uses 3-tier architecture:
1. Data access tier -> handled by repository;
2. Business logic tier -> handled by Service;
3. Presentation tier -> handled by Controllers.

# Instructions to run my project
Docker
1. Clone this repository
2. Run docker
```shell
docker-compose up
```
3. Open the [link](http://localhost:6868/swagger-ui.html) in your browser

In IDE
1. Clone this repository
2. Configure connection to your database in [application.properties](src/main/resources/application.properties)

By changing driver, url to your database, username and password to your own.

3. Build project
 ```shell
mvn clean package -DskipTests
```
4. Open the [link](http://localhost:8080/swagger-ui.html) in your browser

# Instruction of creation
1. Create Master
2. Create Owner
3. Create Car
4. Create Order
5. Create Favor and Goods
