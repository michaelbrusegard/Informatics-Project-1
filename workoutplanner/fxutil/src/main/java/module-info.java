module fxutil {
    exports fxutil.doc;
    requires javafx.base;
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires workoutplanner.core;
    requires com.fasterxml.jackson.databind;
    // opens fxutil.doc to javafx.graphics, javafx.fxml;

}
