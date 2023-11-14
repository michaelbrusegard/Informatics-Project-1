module fxutil {
    requires javafx.controls;
    requires javafx.fxml;

    exports workoutplanner.fxutil;

    opens workoutplanner.fxutil to javafx.graphics, javafx.fxml;
}
