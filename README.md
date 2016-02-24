# 2015-Currency-Exchange

Small application to study software architecture using Spring REST, Spring MVC, Spring Security, Spring ORM and AngularJs

Developed by
    Diego Potapczuk
    potapczuk@gmail.com
    
Developed in
    2 days

Installation dependencies

    The following dependencies are necessary:

    Java 8
    Node
    bower
    maven 3
    
Installation/Run Process

    First you need to download the front end dependencies using the bower:
    
        bower install
        
    To download the backend dependencies and start a test server with an in-memory database, run the following maven command:
    
        mvn clean install tomcat7:run-war -Dspring.profiles.active=test
        
    After that you can access the application using the following URL:
    
        http://localhost:8080
        
    To login in the application the following user is pre-registered:
    
        username: test123 / password: Password2
        
    To execute the application using a non-in-memory database (MySQL) use the following Maven Command:
    
        mvn clean install tomcat7:run-war -Dspring.profiles.active=development
        
    Remember to configure the datasource in the application before that (DevelopmentConfiguration).
