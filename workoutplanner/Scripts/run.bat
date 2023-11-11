@echo off

:: Perform a clean install
cd ..
mvn clean install -DskipTests

:: Open a new terminal and run Spring Boot
start cmd /k "cd ../springboot/restserver && mvn spring-boot:run"

:: Return to the original terminal and run JavaFX
mvn javafx:run
