# Navigate to the parent directory
cd ..

# Perform a clean install
mvn clean install -DskipTests

# Open a new terminal and run Spring Boot
open -a Terminal -n sh -c "cd .. && mvn spring-boot:run"

# Run JavaFX
mvn javafx:run
