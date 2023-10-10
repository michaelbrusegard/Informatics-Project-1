module workoutplanner.ui {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires workoutplanner.core;
    requires fxutil;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    opens workoutplanner.ui to javafx.graphics, javafx.fxml;
}
