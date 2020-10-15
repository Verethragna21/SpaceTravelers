# SpaceTravelers
API Code for Space Travelers

## Contributors:
* **Jorge Canales** - JLP.canales21@gmail.com

### Pre-requisites:

Below technologies are required to run the project.

```
Java 12
```
```
Maven
```

### Used Technologies:

Below technologies are used in this project.

```
Spring Boot
```
```
Lombok (Require the usage of any lombok plugin.)
```
```
JPA
```
```
H2 Database
```
```
OpenAPI Specification
```
```
Jacoco code coverage
```
```
JSON
```
```
Junit 5
```
```
Mockito
```
```
slf4j
```

### How to check the jacoco code coverage report:

First at all please execute in the project folder the below command:

* mvn clean install

Once the mvn command is completed, go to: "target/site/", and open the file "index.html"
 with your prefered browser.
 
### How to run:

Located in the project folder, just run the spring boot run command:

* mvn spring-boot:run


### How to check Swagger API:

After running the app following the steps mentioned in the "How to run" section, go to your
 prefered web browser and use the next URL:

* http://localhost:8080/swagger-ui.html

### How to check openapi.yaml file:

After running the command "mvn clean install" go to the path: "target/" and open the file
 "openapi.yaml" with any swagger editor of your preference.

### How to access to the H2 database console:

After running the app following the steps mentioned in the "How to run" section, go to your
 prefered web browser and use the next URL:

* http://localhost:8080/h2-console/

After that in the login screen type:

* jdbc:h2:mem:george

And press the "Connect" button, user and password are the defaults:

* User name: sa
* Password:
