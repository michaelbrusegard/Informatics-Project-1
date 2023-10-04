module fxutil {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires workoutplanner.core;

    exports fxutil.doc;
    opens fxutil to javafx.graphics, javafx.fxml;

}
