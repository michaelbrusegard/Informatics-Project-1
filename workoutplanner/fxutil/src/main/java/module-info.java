module fxutil {
    exports workoutplanner.fxutil;

    requires javafx.base;
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires workoutplanner.core;
    requires com.fasterxml.jackson.databind;

    opens workoutplanner.fxutil to javafx.graphics, javafx.fxml;

}
