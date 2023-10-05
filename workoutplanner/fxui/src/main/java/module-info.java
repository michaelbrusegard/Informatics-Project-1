module workoutplanner.ui {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires workoutplanner.core;
    requires fxutil;
    opens workoutplanner.ui to javafx.graphics, javafx.fxml;
}
