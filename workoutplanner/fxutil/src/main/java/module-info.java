module fxutil {
    exports workoutplanner.fxutil;

    requires transitive javafx.controls;
    requires transitive javafx.fxml;

    opens workoutplanner.fxutil to javafx.graphics, javafx.fxml;

}
