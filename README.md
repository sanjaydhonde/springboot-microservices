# springboot-microservices
Springboot Microservices for Shopping cart mobile app

This is a sample implementation of springboot microservices.

Features/Technlologies used:
Spring Boot, REST, GSON, Spring data JPA, Java Configuration, Config server, API gateway, Naming server (Eureka), MySQL

List of microservices.
  1. UserService
  2. OrderService
  3. ProductService

Server Configuration:
--------------------- 
  1. API Gateway is configured to run on port 8760
  2. Eureka Server is configured to run on port 8761
  3. Config Server is configured to run on port 8762

Services Confguration:
----------------------
  1. ProductService is configured to run on port 8095
  2. OrderService is configured to run on port 8096
  3. UserService is configured to run on port 8097

Product Service Endpoints: 
--------------------------
  1. /api/products/getProducts
  2. /api/products/getProductCategories
  3. /api/products/getProductSubCategories
  4. /api/products/addProducts
  5. /api/products/addProductCategory
  6. /api/products/addProductVersion
  7. /api/products/getBanners

Order Service Endpoints: 
------------------------
  1. /api/orders/getAllOrders
  2. /api/orders/addOrder

User Service Endpoints: 
-----------------------
  1. /api/users/authenticate
  2. /api/users/registration
  3. /api/users/addCustomer
  4. /api/users/verifyEmail

Sample DB data:
--------------
Services will create DB tables on startup. However, mydb.sql script should be run to populate sample data in mysql DB.


Sample Services Request and Responses:
--------------------------------------
Sample requests/responses of some of the services are captured and shared in folder - 'Service - Request and Responses' for refernce purpose.






