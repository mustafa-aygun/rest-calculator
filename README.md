# Getting Started

### Application 

The application is written for Java SDK 21. It is a basic Java Spring Boot application. The application is under **src/main/java/com/main/restcalculator**. RestCalculator has 4 main packages as listed below:

***controller***: It has the rest controller classes. 

***exceptions***: It is a placeholder for custom exceptions.

***pojo***: It has the custom templates of the return.

***service***: It has the service classes for the rest controllers.

### Application Assumptions

1- It is assumed that the return has no decimal points for integers as in "sum": 42. However, double values are also allowed to sum numbers such as 5,1.5. Therefore, long and double types are used and overflow of them are ignored.

2- It is assumed that input is strict for the operands. Therefore, extra commas are not allowed. For example, ,,5,10,5 or 5,,,10,,5 or 5,10,5,,,,,, are not allowed. 

### Tests

Tests that belong to the application are under **src/test/java/com/main/restcalculator**. There are 2 test files as below.

***RestCalculatorController***: It tests the controller as it is with a mock API call. 

***RestCalculatorService***: It tests the service with its individual functionality for the operations. 

### Javadoc

Javadoc files of the application is generated and located under javadoc folder.

### How to run?

1- IntelliJ: To run the application, it can be simply open as a project in IntelliJ. Pom.xml has all the necessary dependencies to download and make application ready to run. Application can be started from IntelliJ and requests can be send from an application like Postman, or they can be directly opened in a browser with the URL. Also, tests can be run in IntelliJ and has no extra requirement. 

2- JAR: Additional to IntelliJ, there is a JAR file in the repository. The JAR file can be run directly with JAVA 17+. After running the JAR, the same steps to test endpoint as it is running on IntelliJ are valid. Postman or web browsers can be used with an URL. 

3- Docker Build: There is a provided dockerfile in the repository. The application can be run directly with building and running the docker container. Following commands can be applied.

```docker build -t rest-calculator .```

```docker run --rm -p 8081:8081 rest-calculator .```

***Important Notice***: Application runs on port 8081 in IntelliJ. If the port is not empty, it can be modified in application.properties under ***src/main/java/resources***. Also, for the tests, it assumes port 8080 is empty and can initiate a mock web server on that port. Therefore, in the case of running RestCalculatorController tests, be sure that the port is empty or just assign a different one.

***Important Notice-2***: To handle application safely, it is decided to have custom response for some of the exceptions. Instead of direct bad response 400 and empty body. Client is provided with a body that explains the situation along with 400 code. Those can be closed easily with commenting functions under GlobalExceptionHandler. However, some of the tests are also assuming this is the response style that is decided and check for that response messages.

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.5.0/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.5.0/maven-plugin/build-image.html)
