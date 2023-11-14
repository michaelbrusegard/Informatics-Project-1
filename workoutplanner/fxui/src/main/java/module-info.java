module fxui {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires core;
    requires fxutil;

    opens workoutplanner.fxui to javafx.graphics, javafx.fxml;
}
