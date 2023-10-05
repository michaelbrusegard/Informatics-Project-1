module fxutil {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    //requires transitive workoutplanner.core;

    exports fxutil.doc;
    opens fxutil.doc to javafx.graphics, javafx.fxml;

}
