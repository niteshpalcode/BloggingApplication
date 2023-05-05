
# BloggingApplication

This is a RESTful web service for a blogging platform that provides secure CRUD operations with authentication and authorization using Spring Security. The API is built using Java and the Spring Framework.


### Technical Stacks

- Spring Boot 
- Spring Framework
- Spring Data JPA 
- MySQL 
- Hibernate
- Java
- Swagger UI
- Postman
- Spring Security


### Modules
-  Users Login Module
-  Admin Module
-  Users Module
-  Category Module



### Installation & Run
- Before running the API server, you have to update the database configuration inside the application.properties file
- Update the port number, username and password as per your local database configuration
````
    server.port=8888

    spring.datasource.url=jdbc:mysql://localhost:3306/ecomdb;
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=ujjawal
    
````
## API Root Endpoint

`https://localhost:8888/`

`http://localhost:8888/swagger-ui.html`


### some screenshots of the Swagger UI 
![blog](https://user-images.githubusercontent.com/87421981/236360645-f2a9a65a-d749-41a1-adfe-5e7d60b570f9.PNG)


