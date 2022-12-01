# Drone Control Service
This is a dispatch controller that allows clients to communicate with the drones. The specific communicaiton with the drone is outside the scope of this controller.

# Build and Run
Navigate to the root folder of the project and create the WAR package

$ cd /root/folder/of/this/project

$ mvn clean package spring-boot:repackage

Execute the WAR packeage

$ java -jar target/drones-0.0.1-SNAPSHOT.war

# API Docs and usage
Api docs under "/swagger-ui.html" or under "/v2/api-docs"

Default server port 8080


