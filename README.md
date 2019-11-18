This project has been developed by Taner BUYUKYILDIZ as a technical test.

This is a Spring Boot application that can be run by "mvn spring-boot:run" command using the embedded tomcat or can be packaged by "mvn clean package" command as a war file to run under an application server.

The application requires a MongoDB to be installed on 127.0.0.1:27017.

The application, on start up, queries the Fractal Developer Portal Sandbox for raw bank transactions, categorizes them based on a couple static categories.

Categories are cleaned up and recreated with static values every start up.

Categories are stored as nested documents in the Transaction document.

Rest endpoints can be tested on swagger : http://localhost:8090/BraavosClient/swagger-ui.html

After the start up, categories can be listed and using their id, the associated transactions can be listed on the /categories/{ID}/transactions endpoint

