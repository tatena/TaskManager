# TaskManager

Project Setup:

Run "database.sql" under src/main/resources/sql folder to initialize the database for the project. (will be good for local run) 

Authentication:
To log in send auth/getToken request. copy the token and paste in current value of environment task manager, variable token.

Build: 
1. run: "mvn install -DskipTests" to create .jar file for docker-compose (must be run as an IDE command: ctrl+enter on intelliJ) 
2. run docker-compose --build

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/bba815191c2ff166d45f#?env%5BTask-manager%5D=W3sia2V5IjoidG9rZW4iLCJ2YWx1ZSI6IiIsImVuYWJsZWQiOnRydWV9XQ==)