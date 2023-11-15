module fxutil {
    requires transitive javafx.controls;

    exports workoutplanner.fxutil;

    opens workoutplanner.fxutil to javafx.graphics, javafx.fxml;
}
