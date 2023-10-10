module fxutil {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive workoutplanner.core;
    requires com.fasterxml.jackson.databind;

    exports workoutplanner.fxutil;

    opens workoutplanner.fxutil to javafx.graphics, javafx.fxml;

}
